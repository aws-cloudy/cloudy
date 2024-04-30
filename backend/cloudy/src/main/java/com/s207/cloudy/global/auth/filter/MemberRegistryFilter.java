package com.s207.cloudy.global.auth.filter;


import com.s207.cloudy.domain.members.application.MemberService;
import com.s207.cloudy.domain.members.dao.MemberRepository;
import com.s207.cloudy.domain.members.entity.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;



@RequiredArgsConstructor
public class MemberRegistryFilter  extends OncePerRequestFilter {

    private final MemberService memberService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(SecurityContextHolder.getContext().getAuthentication()!=null){
            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            if(!memberService.isExist(userId)){
                Member member = new Member(userId, null, userId);
                memberService.save(member);
            }
        }

        filterChain.doFilter(request, response);
    }
}
