package com.s207.cloudy.domain.health.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.s207.cloudy.global.auth.service.JwtService;
import com.s207.cloudy.global.auth.service.JwtServiceImpl;
import com.s207.cloudy.global.config.SecurityConfig;
import com.s207.cloudy.infra.cognito.api.CognitoServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = HealthController.class)
@AutoConfigureMockMvc(addFilters = false)
class HealthControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void 관리자는_헬스체크_api로_서버상태를_확인할_수_있다()throws Exception{
        mockMvc.perform(get("/api/v1/health/test"))
                .andExpect(status().isOk());
    }


}