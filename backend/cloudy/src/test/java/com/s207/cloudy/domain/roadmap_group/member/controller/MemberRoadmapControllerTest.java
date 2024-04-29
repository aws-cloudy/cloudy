package com.s207.cloudy.domain.roadmap_group.member.controller;

import com.s207.cloudy.global.auth.api.AuthController;
import com.s207.cloudy.global.auth.service.JwtService;
import com.s207.cloudy.global.config.SecurityConfig;
import com.s207.cloudy.global.auth.error.exception.AuthorizationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = {MemberRoadmapController.class, AuthController.class},
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class))
class MemberRoadmapControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private JwtService jwtService;



    @Test
    @DisplayName("로그인이 되어있지 않다면 403 에러를 반환한다.")
    void should_401_when_not_login() throws Exception{

        given(
                jwtService.isTokenValid(any())
        ).willThrow(AuthorizationException.class);

        mockMvc.perform(get("/api/v1/my/roadmaps"))
                .andExpect(status().isForbidden());
    }




}