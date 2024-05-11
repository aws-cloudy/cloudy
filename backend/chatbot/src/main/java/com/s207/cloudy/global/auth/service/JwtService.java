package com.s207.cloudy.global.auth.service;


import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface JwtService {

    boolean isTokenValid(String token) throws TokenExpiredException;

    Optional<String> extractAccessToken(HttpServletRequest request);






}
