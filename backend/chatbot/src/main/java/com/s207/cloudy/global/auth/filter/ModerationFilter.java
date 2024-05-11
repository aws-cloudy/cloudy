package com.s207.cloudy.global.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;
import com.s207.cloudy.global.infra.modetation.ModerationService;
import java.io.IOException;


@RequiredArgsConstructor
public class ModerationFilter extends OncePerRequestFilter
{
   private final ModerationService moderationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {



    }
}
