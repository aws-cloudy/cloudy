package com.s207.cloudy.search.domain.learning.application;

import com.s207.cloudy.search.domain.learning.dto.SearchListItem;
import com.s207.cloudy.search.domain.learning.dto.SearchQueryRes;
import com.s207.cloudy.search.global.util.RedisUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import com.s207.cloudy.search.domain.learning.dto.SearchListRes;
import com.s207.cloudy.search.domain.learning.dto.SearchReq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

import static com.s207.cloudy.search.dummy.learning.DummyLearning.getDummySearchItem1;
import static org.mockito.Mockito.*;


@SpringJUnitConfig(SearchServiceImpl.class)
class SearchServiceImplTest {

    @Autowired
    private SearchService searchService;

    @MockBean
    private OpensearchServiceImpl opensearchService;

    @MockBean
    private RedisUtils redisUtils;


    private SearchListItem dummyItem1;
    private List<SearchListItem> dummyList;

    @BeforeEach
    void setUp() {
        dummyItem1 = getDummySearchItem1();
        dummyList = List.of(dummyItem1);
    }

    @Test
    @DisplayName("검색어를 입력할 때, 자동완성된 목록이 캐시에 있다면 캐시에서 가져온다.")
    void getAutoCompleteListWithCacheHit() {
        // Given
        SearchReq req = new SearchReq("Introduction to Amazon Elastic MapReduce (EMR) (Korean)", 1);
        SearchListRes expectedResult = new SearchListRes(dummyList);

        when(redisUtils.getData(req.getQuery(), SearchListRes.class)).thenReturn(java.util.Optional.of(expectedResult));

        // When
        SearchListRes actualResult = searchService.getAutoCompleteList(req);

        // Then
        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("검색어를 입력할 때, 자동완성된 목록이 캐시에 없다면 오픈서치에서 가져온다.")
    void getAutoCompleteListWithoutCacheHit() {
        // Given
        SearchReq req = new SearchReq("Introduction to Amazon Elastic MapReduce (EMR) (Korean)", 1);
        SearchListRes expectedResult = new SearchListRes(dummyList);

        when(redisUtils.getData(req.getQuery(), SearchListRes.class)).thenReturn(java.util.Optional.empty());
        when(opensearchService.searchList(req.getQuery(), req.getCount())).thenReturn(expectedResult);

        // When
        SearchListRes actualResult = searchService.getAutoCompleteList(req);

        // Then
        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
        verify(redisUtils, times(1)).saveData(anyString(), any(), anyLong());
    }

    @Test
    @DisplayName("검색할 때, 검색어가 오픈서치에 존재하면 해당 검색어를 그대로 반환한다.")
    void getFinalQueryWhenExistInOpensearch() {
        // Given
        String query = "Introduction to Amazon Elastic MapReduce (EMR) (Korean)";
        SearchListRes searchResult  = new SearchListRes(dummyList);

        when(opensearchService.isQueryExist(query)).thenReturn(searchResult);

        // When
        SearchQueryRes actualResult = searchService.getFinalQuery(query);

        // Then
        verify(opensearchService, times(1)).addQueryIfNotExist(any(), anyString());
        verify(opensearchService, times(1)).increaseCounter(any());
        Assertions.assertThat(actualResult.getQuery()).isEqualTo(query);
        Assertions.assertThat(actualResult.getModifiedQuery()).isNull();
    }

    @Test
    @DisplayName("검색할 때, 오타 교정된 검색어가 오픈서치에 존재하면, 해당 검색어와 오타 교정된 검색어를 함께 반환한다.")
    void getFinalModifiedQueryWhenExistInOpensearch() {
        // Given
        String query = "Intreduction to Amazen Elestic MapReduce (EMR) (Korean)";
        String modifiedResult  = "Introduction to Amazon Elastic MapReduce (EMR) (Korean)";

        when(opensearchService.isQueryExist(query)).thenReturn(new SearchListRes(new ArrayList<>()));
        when(opensearchService.isModifiedQueryExist(query)).thenReturn(modifiedResult);

        // When
        SearchQueryRes actualResult = searchService.getFinalQuery(query);

        // Then
        Assertions.assertThat(actualResult.getQuery()).isEqualTo(query);
        Assertions.assertThat(actualResult.getModifiedQuery()).isEqualTo(modifiedResult);
    }

    @Test
    @DisplayName("검색할 때, 검색어 및 오타 교정된 검색어 모두 오픈서치에 존재하지 않으면, 해당 검색어를 그대로 반환한다.")
    void getFinalQueryWhenNotExistInOpensearch() {
        // Given
        String query = "abcdefghijklmnopqrstuvwxyz";

        when(opensearchService.isQueryExist(query)).thenReturn(new SearchListRes(new ArrayList<>()));
        when(opensearchService.isModifiedQueryExist(query)).thenReturn(query);

        // When
        SearchQueryRes actualResult = searchService.getFinalQuery(query);

        // Then
        Assertions.assertThat(actualResult.getQuery()).isEqualTo(query);
        Assertions.assertThat(actualResult.getModifiedQuery()).isNull();
    }

}
