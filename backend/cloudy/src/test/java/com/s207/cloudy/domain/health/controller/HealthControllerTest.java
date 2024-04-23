package com.s207.cloudy.domain.health.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class HealthControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//
//    @Test
//    void 관리자는_헬스체크_api로_서버상태를_확인할_수_있다()throws Exception{
//        mockMvc.perform(get("/health"))
//                .andExpect(status().isOk());
//    }


}