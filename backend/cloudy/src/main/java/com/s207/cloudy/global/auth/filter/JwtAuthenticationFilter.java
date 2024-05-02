package com.s207.cloudy.global.auth.filter;

import com.auth0.jwt.exceptions.JWTDecodeException;
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
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


import static com.s207.cloudy.global.error.enums.ErrorCode.UNAUTHORIZED;


@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!request.getServletPath().matches("^/api/v1/bookmarks/.*")&&!request.getServletPath().equals("/api/v1/bookmarks")){
            filterChain.doFilter(request, response);
            return;
        }
        try{
            jwtService.extractAccessToken(request)
                    .filter(jwtService::isTokenValid)
                    .orElseThrow(()-> new AuthorizationException(UNAUTHORIZED));
        }catch(Exception e) {
            log.error("{}", e.getMessage());
        }

        filterChain.doFilter(request, response);

    }






}
