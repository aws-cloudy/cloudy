package com.s207.cloudy.global.auth.filter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s207.cloudy.global.error.dto.CommonErrorResponse;
import com.s207.cloudy.global.error.enums.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) {
        try{
            filterChain.doFilter(request, response);
        }catch (TokenExpiredException e){
            log.error("exception : 엑세스 토큰 기간 만료");
            setErrorResponse(response, HttpStatus.UNAUTHORIZED, "기간만료");
        }catch (IllegalArgumentException e){
            log.error("exception : 잘못된 엑세스 토큰");
            setErrorResponse(response, HttpStatus.UNAUTHORIZED, "액세스 토큰이 존재하지 않습니다.");
        }catch (Exception e){
            log.error("exception : {}", e);
            setErrorResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    private void setErrorResponse(
            HttpServletResponse response,
            HttpStatus status,
            String errorMassage
    ){
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try{
            String jsonResponse = objectMapper.writeValueAsString(
                    CommonErrorResponse
                            .builder()
                            .code(ErrorCode.UNAUTHORIZED.getCode())
                            .message(ErrorCode.UNAUTHORIZED.getMessage())
                            .build()

            );
            log.error("[ExceptionHandlerFilter setErrorResponse] :: jsonResponse : {}",jsonResponse);
            response.getWriter().write(jsonResponse);
        }catch (IOException e){
            log.error(e.toString());
        }
    }
}