package com.s207.cloudy.global.auth.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.s207.cloudy.domain.members.entity.Member;
import com.s207.cloudy.global.auth.api.Payload;
import com.s207.cloudy.global.auth.error.exception.AuthorizationException;
import com.s207.cloudy.global.auth.util.RsaKeyProvider;
import com.s207.cloudy.global.auth.util.RsaUtil;
import com.s207.cloudy.global.error.enums.ErrorCode;

import com.s207.cloudy.global.infra.cognito.CognitoServiceClient;
import com.s207.cloudy.global.infra.cognito.dto.JwkDto;
import com.s207.cloudy.global.infra.cognito.dto.JwksDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.s207.cloudy.global.auth.api.Payload.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {


    private final CognitoServiceClient cognitoServiceClient;
    private final RsaUtil rsaUtils;
    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();




    @Override
    public boolean isTokenValid(String token) throws TokenExpiredException {

        var decodedJWT = JWT.decode(token);

        //jwks를 사용하여 jwt 검증
        var jwk = getJwk(decodedJWT.getHeaderClaim(KID.getData()).asString());

        var algorithm = buildAlgorithm((jwk));

        var userId = extractPayloadData(SUB.getData(), algorithm, token);

        var name = extractPayloadData(NAME.getData(), algorithm, token);

        log.info("[JwtServiceImpl isTokenValid] user가 접속하였습니다. ::{}, {}", userId, name);
        generateAuthentication(userId, name);

        return true;
    }

    private String extractPayloadData(String claimType, Algorithm algorithm, String token){
        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getClaim(claimType)
                .asString();
    }

    private Algorithm buildAlgorithm(JwkDto jwk){
        return Algorithm.RSA256(new RsaKeyProvider(rsaUtils, jwk));
    }

    @Override
    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(ACCESS_HEADER.getData()))
                .filter(accessToken -> accessToken.startsWith(BEARER.getData()))
                .map(accessToken -> accessToken.replace(BEARER.getData(), ""))
                .map(accessToken-> accessToken.replace(" ", ""));

    }
    


    private JwkDto getJwk(String kid){
        JwksDto jwks = cognitoServiceClient.getJwks().getBody();

        if(jwks==null){
            throw new AuthorizationException(ErrorCode.COGNITO_SERVICE_ERROR);
        }

        return jwks
            .keys()
            .stream()
            .filter(jwk -> jwk.equalsKid(kid))
            .findFirst()
            .orElseThrow(()-> new AuthorizationException(ErrorCode.UNAUTHORIZED));

    }


    private void generateAuthentication(String userId, String userName){

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("user"));
        Member member = new Member(userId,userName, "1", authorities);
        log.error("[JwtServiceImpl generateAuthentication] :: {}", member);
        Authentication authentication = new UsernamePasswordAuthenticationToken(member, null,
                authoritiesMapper.mapAuthorities(member.getAuthorities()));
        // Authentication 객체를 SecurityContext에 설정
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("authentication :: {}", SecurityContextHolder.getContext().getAuthentication().getName());

    }




}




