package com.s207.cloudy.domain.learning.api;

import com.s207.cloudy.domain.learning.dto.LearningItem;
import com.s207.cloudy.domain.learning.application.LearningService;
import com.s207.cloudy.dummy.learning.DummyLearning;
import com.s207.cloudy.global.auth.service.JwtService;
import com.s207.cloudy.global.config.aop.CustomValidationAdvice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LearningController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({AopAutoConfiguration.class, CustomValidationAdvice.class})
class LearningControllerTest {

    @MockBean
    JwtService jwtService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LearningController learningController;

    @MockBean
    LearningService mockLearningService;

    @Autowired
    CustomValidationAdvice customValidationAdvice;

    LearningItem dummyItem1, dummyItem2, dummyItem3, dummyItem4;

    @BeforeEach
    void setUp() {
        dummyItem1 = DummyLearning.getDummyLearningItem1();
        dummyItem2 = DummyLearning.getDummyLearningItem2();
        dummyItem3 = DummyLearning.getDummyLearningItem3();
        dummyItem4 = DummyLearning.getDummyLearningItem4();
    }

    @Test
    @DisplayName("검색어 수정전의 전체 학습 목록을 조회하고, 200 OK를 반환한다.")
    void getListSuccess() throws Exception{
        List<LearningItem> dummyList = List.of(dummyItem1, dummyItem2);

        given(mockLearningService.getLearnings(any()))
                .willReturn(DummyLearning.getDummyLearningListRes(dummyList, false));

        mockMvc.perform(get("/api/v1/learnings/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.learningList[0].learningId", equalTo(dummyList.get(0).getLearningId()), Integer.class))
                .andExpect(jsonPath("$.learningList[0].thumbnail", equalTo(dummyList.get(0).getThumbnail()), String.class))
                .andExpect(jsonPath("$.learningList[0].serviceType", equalTo(dummyList.get(0).getServiceType()), String.class))
                .andExpect(jsonPath("$.learningList[0].title", equalTo(dummyList.get(0).getTitle()), String.class))
                .andExpect(jsonPath("$.learningList[0].summary", equalTo(dummyList.get(0).getSummary()), String.class))
                .andExpect(jsonPath("$.learningList[0].duration", equalTo(dummyList.get(0).getDuration()), String.class))
                .andExpect(jsonPath("$.learningList[0].difficulty", equalTo(dummyList.get(0).getDifficulty()), String.class))
                .andExpect(jsonPath("$.learningList[0].link", equalTo(dummyList.get(0).getLink()), String.class))
                .andExpect(jsonPath("$.isModified", equalTo(false), Boolean.class))
                .andDo(print());
    }

    @Test
    @DisplayName("파라미터들을 정상 입력 시 검색어 수정전의 전체 학습 목록을 조회하고, 200 OK를 반환한다.")
    void getListByParamsSuccess() throws Exception{
        List<LearningItem> dummyList = List.of(dummyItem1, dummyItem2);

        given(mockLearningService.getLearnings(any()))
                .willReturn(DummyLearning.getDummyLearningListRes(dummyList, false));

        mockMvc.perform(get("/api/v1/learnings/search?page=1&pageSize=5&difficulty=1,2&type=Digital_Course,Exam_Preparation"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.learningList[0].learningId", equalTo(dummyList.get(0).getLearningId()), Integer.class))
                .andExpect(jsonPath("$.learningList[0].thumbnail", equalTo(dummyList.get(0).getThumbnail()), String.class))
                .andExpect(jsonPath("$.learningList[0].serviceType", equalTo(dummyList.get(0).getServiceType()), String.class))
                .andExpect(jsonPath("$.learningList[0].title", equalTo(dummyList.get(0).getTitle()), String.class))
                .andExpect(jsonPath("$.learningList[0].summary", equalTo(dummyList.get(0).getSummary()), String.class))
                .andExpect(jsonPath("$.learningList[0].duration", equalTo(dummyList.get(0).getDuration()), String.class))
                .andExpect(jsonPath("$.learningList[0].difficulty", equalTo(dummyList.get(0).getDifficulty()), String.class))
                .andExpect(jsonPath("$.learningList[0].link", equalTo(dummyList.get(0).getLink()), String.class))
                .andExpect(jsonPath("$.isModified", equalTo(false), Boolean.class))
                .andDo(print());
    }

    @Test
    @DisplayName("파라미터들을 비정상 입력 시, 400 Bad Request를 반환한다.")
    void getListByParamsFail() throws Exception{
        List<LearningItem> dummyList = List.of(dummyItem1, dummyItem2);

        given(mockLearningService.getLearnings(any()))
                .willReturn(DummyLearning.getDummyLearningListRes(dummyList, false));

        mockMvc.perform(get("/api/v1/learnings/search?difficulty=-1,2&type=Digital Course"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorMap.difficulty.code",equalTo("CE001"), String.class))
                .andExpect(jsonPath("$.errorMap.difficulty.message",equalTo("존재하지 않는 난이도입니다"), String.class))
                .andExpect(jsonPath("$.errorMap.type.code",equalTo("CE001"), String.class))
                .andExpect(jsonPath("$.errorMap.type.message",equalTo("존재하지 않는 강의 분류입니다"), String.class))
                .andDo(print());
    }

    @Test
    @DisplayName("페이지 크기가 1부터 100 사이가 아니라면, 400 Bad Request를 반환한다.")
    void getListByPageSizeFail() throws Exception{

        mockMvc.perform(get("/api/v1/learnings/search?pageSize=-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorMap.pageSize.code",equalTo("CE002"), String.class))
                .andExpect(jsonPath("$.errorMap.pageSize.message",equalTo("1이상 100이하의 값만 가능합니다"), String.class))
                .andDo(print());
    }


    @Test
    @DisplayName("페이지 번호가 음수라면, 400 Bad Request를 반환한다.")
    void getListByPageFail() throws Exception{

        mockMvc.perform(get("/api/v1/learnings/search?page=-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorMap.page.code",equalTo("CE003"), String.class))
                .andExpect(jsonPath("$.errorMap.page.message",equalTo("1이상의 값만 가능합니다"), String.class))
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 시, 지정한 개수만큼 해당 직무번호와 일치하는 전체 학습 목록을 랜덤하게 조회하고, 200 OK를 반환한다.")
    void getListByJobWhenLoginSuccess() throws Exception {
        List<LearningItem> dummyList = List.of(dummyItem3, dummyItem4);

        given(mockLearningService.getLearningsByJob(anyInt(), anyInt()))
                .willReturn(DummyLearning.getDummyLearningListRes(dummyList));

        mockMvc.perform(get("/api/v1/my/learnings/search/job/3?count=2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.learningList",hasSize(2)))
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 시, 지정한 개수가 1부터 100 사이가 아니라면 400 Bad Request를 반환한다.")
    void getListByJobWhenLoginFail() throws Exception {
        mockMvc.perform(get("/api/v1/my/learnings/search/job/1?count=-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorMap.count.code",equalTo("CE001"), String.class))
                .andExpect(jsonPath("$.errorMap.count.message",equalTo("1이상 100이하의 값만 가능합니다"), String.class))
                .andDo(print());
    }

    @Test
    @DisplayName("로그아웃 시, 지정한 개수만큼 전체 학습 목록을 랜덤하게 조회하고, 200 OK를 반환한다.")
    void getListByJobWhenLogoutSuccess() throws Exception {
        List<LearningItem> dummyList = List.of(dummyItem1, dummyItem2);

        given(mockLearningService.getLearningsByJob(anyInt()))
                .willReturn(DummyLearning.getDummyLearningListRes(dummyList));

        mockMvc.perform(get("/api/v1/learnings/search/job?count=2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.learningList",hasSize(2)))
                .andDo(print());
    }

    @Test
    @DisplayName("로그아웃 시, 지정한 개수가 1부터 100 사이가 아니라면 400 Bad Request를 반환한다.")
    void getListByJobWhenLogoutFail() throws Exception {
        mockMvc.perform(get("/api/v1/learnings/search/job?count=-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorMap.count.code",equalTo("CE001"), String.class))
                .andExpect(jsonPath("$.errorMap.count.message",equalTo("1이상 100이하의 값만 가능합니다"), String.class))
                .andDo(print());
    }
}
