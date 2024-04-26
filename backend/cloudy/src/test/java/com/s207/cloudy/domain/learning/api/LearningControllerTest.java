package com.s207.cloudy.domain.learning.api;

import com.s207.cloudy.domain.learning.controller.LearningController;
import com.s207.cloudy.domain.learning.dto.LearningItem;
import com.s207.cloudy.domain.learning.service.LearningService;
import com.s207.cloudy.dummy.learning.DummyLearning;
import com.s207.cloudy.global.config.aop.CustomValidationAdvice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LearningController.class)
@Import({AopAutoConfiguration.class, CustomValidationAdvice.class})
class LearningControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LearningController learningController;

    @MockBean
    LearningService mockLearningService;

    @Autowired
    CustomValidationAdvice customValidationAdvice;

    LearningItem dummyItem1;
    LearningItem dummyItem2;

    @BeforeEach
    void setUp() {
        dummyItem1 = DummyLearning.getDummyLearningItem1();
        dummyItem2 = DummyLearning.getDummyLearningItem2();
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
    @DisplayName("페이지 크기가 1부터 100 사이가 아니라면, 400 Bad Request를 반환한다.")
    void getListByPageSizeFail() throws Exception{

        mockMvc.perform(get("/api/v1/learnings/search?pageSize=-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.pageSize.code",equalTo("CE002"), String.class))
                .andExpect(jsonPath("$.pageSize.message",equalTo("1이상 100이하의 값만 가능합니다"), String.class))
                .andDo(print());
    }


    @Test
    @DisplayName("페이지 번호가 음수라면, 400 Bad Request를 반환한다.")
    void getListByPageFail() throws Exception{

        mockMvc.perform(get("/api/v1/learnings/search?page=-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.page.code",equalTo("CE003"), String.class))
                .andExpect(jsonPath("$.page.message",equalTo("1이상의 값만 가능합니다"), String.class))
                .andDo(print());
    }

}
