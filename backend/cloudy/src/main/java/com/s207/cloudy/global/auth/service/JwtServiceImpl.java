package com.s207.cloudy.global.auth.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import com.s207.cloudy.infra.cognito.api.CognitoServiceClient;
import com.s207.cloudy.infra.cognito.dto.JwkDto;
import com.s207.cloudy.infra.cognito.dto.JwksDto;
import com.s207.cloudy.global.auth.util.RsaUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {


    private final CognitoServiceClient cognitoServiceClient;
    private final RsaUtil RsaUtils;


    private final static String ACCESS_HEADER = "Authorization";
    private final static String BEARER= "Bearer";

    @Override
    public boolean isTokenValid(String token){
        var decodedJWT = JWT.decode(token);

        //jwks를 사용하여 jwt 검증
        var jwk = getJwk(decodedJWT.getHeaderClaim("kid").asString());

        var algorithm = buildAlgorithm((jwk));

        //검증을 진행한다.
        JWT.require(algorithm)
                .build()
                .verify(token);

        return true;
    }

    private Algorithm buildAlgorithm(JwkDto jwk){
        return Algorithm.RSA256(new RSAKeyProvider() {
            @Override
            public RSAPublicKey getPublicKeyById(String keyId) {
                // JWKS를 가져오고 kid와 일치하는 키를 반환
                try {
                    byte[] modulus = Base64.getUrlDecoder().decode(jwk.n());
                    byte[] exponent = Base64.getUrlDecoder().decode(jwk.e());
                    return RsaUtils.getRSAPublicKey(modulus, exponent);
                } catch (Exception e) {
                    //todo 에러코드 정의
                    throw new RuntimeException("jwk에서 퍼블릭 키 생성 중 오류");
                }
            }

            @Override
            public RSAPrivateKey getPrivateKey() {
                return null;
            }

            @Override
            public String getPrivateKeyId() {
                return null;
            }
        });
    }

    @Override
    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(ACCESS_HEADER))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }




    private JwkDto getJwk(String kid){
        JwksDto jwks = cognitoServiceClient.getJwks().getBody();

        if(jwks==null){
            throw new RuntimeException("cognito 통신실패");
        }

        return jwks
            .keys()
            .stream()
            .filter(jwk -> jwk.equalsKid(kid))
            .findFirst()
            .orElseThrow(()-> new RuntimeException("kid가 일치하는 jwk가 존재하지 않습니다."));

    }







}
