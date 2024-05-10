package com.s207.cloudy.search.domain.learning.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.s207.cloudy.search.domain.learning.application.SearchService;
import com.s207.cloudy.search.domain.learning.dto.SearchListItem;
import com.s207.cloudy.search.domain.learning.dto.SearchQueryRes;
import com.s207.cloudy.search.domain.learning.dto.SearchReq;
import com.s207.cloudy.search.dummy.learning.DummyLearning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.s207.cloudy.search.dummy.learning.DummyLearning.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SearchController.class)
public class SearchControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SearchService mockSearchService;

    @Autowired
    SearchController searchController;

    @Autowired
    ObjectMapper objectMapper;

    SearchListItem dummyItem1, dummyItem2, dummyItem3;
    List<SearchListItem> dummyList;

    @BeforeEach
    void setUp() {
        dummyItem1 = DummyLearning.getDummySearchItem1();
        dummyItem2 = DummyLearning.getDummySearchItem2();
        dummyItem3 = DummyLearning.getDummySearchItem3();
        dummyList = List.of(dummyItem1, dummyItem2, dummyItem3);
    }

    @Test
    @DisplayName("검색어를 입력하면, 자동완성된 목록과 200 OK를 반환한다.")
    void getWhenQueryIsExistSuccess() throws Exception {
        SearchReq req = new SearchReq();
        req.setQuery("elastic");
        req.setCount(3);

        given(mockSearchService.getAutoCompleteList(any(SearchReq.class)))
                .willReturn(DummyLearning.getDummySearchList(dummyList));

        mockMvc.perform(post("/api/v1/learnings/search/autocomplete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.searchList.size()", equalTo(dummyList.size()), Integer.class))
                .andExpect(jsonPath("$.searchList[0].learningId", equalTo(dummyList.get(0).getLearningId()), String.class))
                .andExpect(jsonPath("$.searchList[0].title", equalTo(dummyList.get(0).getTitle()), String.class))
                .andExpect(jsonPath("$.searchList[0].documentId", equalTo(dummyList.get(0).getDocumentId()), String.class))
                .andDo(print());
    }

    @Test
    @DisplayName("검색어를 입력하지 않으면, 빈 자동완성된 목록과 200 OK를 반환한다.")
    void getWhenQueryIsNotExistSuccess() throws Exception {
        SearchReq req = new SearchReq();
        req.setCount(3);

        given(mockSearchService.getAutoCompleteList(any(SearchReq.class)))
                .willReturn(DummyLearning.getDummySearchList(null));

        mockMvc.perform(post("/api/v1/learnings/search/autocomplete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.searchList", equalTo(null), List.class))
                .andDo(print());
    }

    @Test
    @DisplayName("가져올 개수를 입력하지 않으면, 기본 3개로 설정된 목록과 200 OK를 반환한다.")
    void getWhenCountIsNotExistSuccess() throws Exception {
        SearchReq req = new SearchReq();
        req.setQuery("elastic");

        given(mockSearchService.getAutoCompleteList(any(SearchReq.class)))
                .willReturn(DummyLearning.getDummySearchList(dummyList));

        mockMvc.perform(post("/api/v1/learnings/search/autocomplete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.searchList.size()", equalTo(dummyList.size()), Integer.class))
                .andExpect(jsonPath("$.searchList[0].learningId", equalTo(dummyList.get(0).getLearningId()), String.class))
                .andExpect(jsonPath("$.searchList[0].title", equalTo(dummyList.get(0).getTitle()), String.class))
                .andExpect(jsonPath("$.searchList[0].documentId", equalTo(dummyList.get(0).getDocumentId()), String.class))
                .andDo(print());
    }

    @Test
    @DisplayName("유효하지 않은 요청이라면, 400 BAD_REQUEST를 반환한다.")
    void getSearchAutoCompleteListFail() throws Exception {
        mockMvc.perform(post("/api/v1/learnings/search/autocomplete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", equalTo("CE001"), String.class))
                .andExpect(jsonPath("$.message", equalTo("API 요청 URL의 프로토콜, 파라미터 등에 오류가 있습니다."), String.class))
                .andDo(print());
    }

    @Test
    @DisplayName("오타가 포함된 검색어를 입력하면, 오타 교정된 검색어와 200 OK를 반환한다.")
    void getFinalQueryModifiedSuccess() throws Exception {
        SearchQueryRes res = getDummySearchQueryWhenModified();

        given(mockSearchService.getFinalQuery(any()))
                .willReturn(res);

        mockMvc.perform(get("/api/v1/learnings/search/final?query=" + res.getQuery()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.query", equalTo("amezon"), String.class))
                .andExpect(jsonPath("$.modifiedQuery", equalTo("amazon"), String.class))
                .andDo(print());
    }

    @Test
    @DisplayName("오타가 포함되지 않은 검색어를 입력하면, 검색어 그대로와 200 OK를 반환한다.")
    void getFinalQuerySuccess() throws Exception {
        SearchQueryRes res = getDummySearchQueryWhenExist();

        given(mockSearchService.getFinalQuery(any()))
                .willReturn(res);

        mockMvc.perform(get("/api/v1/learnings/search/final?query=" + res.getQuery()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.query", equalTo("amazon"), String.class))
                .andExpect(jsonPath("$.modifiedQuery").doesNotExist())
                .andDo(print());
    }

    @Test
    @DisplayName("오타 교정 결과가 없는 검색어를 입력하면, 검색어 그대로와 200 OK를 반환한다.")
    void getFinalQueryWhenNotExistAndNotModifiedSuccess() throws Exception {
        SearchQueryRes res = getDummySearchQueryWhenNotExistAndNotModified();

        given(mockSearchService.getFinalQuery(any()))
                .willReturn(res);

        mockMvc.perform(get("/api/v1/learnings/search/final?query=" + res.getQuery()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.query", equalTo("abcdefghijklmsopqrstuvwxyz"), String.class))
                .andExpect(jsonPath("$.modifiedQuery").doesNotExist())
                .andDo(print());
    }

}
