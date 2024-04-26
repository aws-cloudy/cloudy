package com.s207.cloudy.domain.roadmapGroup.member.controller;

import com.s207.cloudy.domain.roadmapGroup.roadmap.api.RoadmapController;
import com.s207.cloudy.global.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = MemberRoadmapController.class,
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class))
class MemberRoadmapControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("로그인이 되어있지 않다면 403에러를 반환한다.")
    void should_403_when_not_login() throws Exception{

        mockMvc.perform(get("/api/v1/roadmaps/my"))
                .andExpect(status().isForbidden());
    }


}