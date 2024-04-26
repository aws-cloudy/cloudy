package com.s207.cloudy.global.auth.service;


import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface JwtService {

    boolean isTokenValid(String token);

    Optional<String> extractAccessToken(HttpServletRequest request);






}
