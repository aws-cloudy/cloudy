package com.s207.cloudy.global.auth.api;

import com.s207.cloudy.domain.members.application.MemberService;
import com.s207.cloudy.global.auth.error.exception.AuthorizationException;
import com.s207.cloudy.global.auth.filter.ExceptionHandlerFilter;
import com.s207.cloudy.global.auth.service.JwtService;
import com.s207.cloudy.global.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {AuthController.class, ExceptionHandlerFilter.class},
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class))
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("로그인이 되어있지 않은 회원이 마이페이지를 접근하면 401 에러를 반환한다.")
    void should_401_when_not_login_user_access_mypage() throws Exception {

        given(
                jwtService.isTokenValid(any())
        ).willThrow(AuthorizationException.class);

        given(
                memberService.isExist(any())
        ).willReturn(true);

        mockMvc.perform(get("/api/v1/bookmarks"))
                .andExpect(status().isForbidden());
    }


}