package com.s207.cloudy.global.auth.filter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.s207.cloudy.domain.members.entity.Member;
import com.s207.cloudy.global.auth.service.JwtService;
import com.s207.cloudy.global.auth.error.exception.AuthorizationException;
import com.s207.cloudy.global.error.enums.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.s207.cloudy.global.error.enums.ErrorCode.UNAUTHORIZED;


@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static DefaultRedirectStrategy defaultRedirectStrategy = new DefaultRedirectStrategy();

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            jwtService.extractAccessToken(request)
                    .filter(jwtService::isTokenValid)
                    .orElseThrow(()-> new AuthorizationException(UNAUTHORIZED));
        }catch(AuthorizationException e){
            e.printStackTrace();
            log.error("{}", e.getMessage());
            response.setStatus(401);
            response.getWriter().write(e.getMessage());
        }catch(AccessDeniedException e){
            e.printStackTrace();
            log.error("{}", e.getMessage());
            response.setStatus(401);
            response.getWriter().write(e.getMessage());
        }catch(TokenExpiredException e){
            e.printStackTrace();
            log.error("{}", e.getMessage());
            response.setStatus(401);
            response.getWriter().write("토큰의 유효기간이 만료되었습니다.");
        }

        filterChain.doFilter(request, response);

    }






}
