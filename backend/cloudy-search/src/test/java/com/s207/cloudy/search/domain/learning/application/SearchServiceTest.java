package com.s207.cloudy.search.domain.learning.application;

import com.s207.cloudy.search.domain.learning.dto.SearchListItem;
import com.s207.cloudy.search.domain.learning.dto.SearchQueryRes;
import com.s207.cloudy.search.global.util.RedisUtils;
import com.s207.cloudy.search.global.util.SearchResultMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.opensearch.client.RestHighLevelClient;

import com.s207.cloudy.search.domain.learning.dto.SearchListRes;
import com.s207.cloudy.search.domain.learning.dto.SearchReq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

import static com.s207.cloudy.search.dummy.learning.DummyLearning.getDummySearchItem1;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig(SearchServiceImpl.class)
class SearchServiceImplTest {

    @Mock
    private RestHighLevelClient client;

    @Mock
    private SearchResultMapper mapper;

    @Mock
    private RedisUtils redisUtils;

    @Autowired
    private SearchService searchService;

    private SearchListItem dummyItem1;
    private List<SearchListItem> dummyList;

    @BeforeEach
    void setUp() {
        dummyItem1 = getDummySearchItem1();
        dummyList = List.of(dummyItem1);
        MockitoAnnotations.initMocks(this);
        searchService = new SearchServiceImpl(client, mapper, redisUtils);
    }

    @Test
    @DisplayName("검색어를 입력할 때, 자동완성된 목록이 캐시에 있다면 캐시에서 가져온다.")
    void getAutoCompleteListWithCacheHit() {
        // Given
        SearchReq req = new SearchReq("Introduction to Amazon Elastic MapReduce (EMR) (Korean)", 1);
        SearchListRes expectedResult = new SearchListRes(dummyList);

        when(searchService.searchListFromCache(req.getQuery())).thenReturn(java.util.Optional.of(expectedResult));

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

        when(searchService.searchListFromCache(req.getQuery())).thenReturn(java.util.Optional.empty());
        when(searchService.searchListFromOpensearch(req.getQuery(), req.getCount())).thenReturn(expectedResult);


        // When
        SearchListRes actualResult = searchService.getAutoCompleteList(req);

        // Then
        Assertions.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("검색할 때, 검색어가 오픈서치에 존재하면 해당 검색어를 그대로 반환한다.")
    void getFinalQueryWhenExistInOpensearch() {
        // Given
        String query = "Introduction to Amazon Elastic MapReduce (EMR) (Korean)";
        SearchListRes searchResult  = new SearchListRes(dummyList);

        when(searchService.isQueryExistInOpensearch(query)).thenReturn(searchResult);

        // When
        SearchQueryRes actualResult = searchService.getFinalQuery(query);

        // Then
        Assertions.assertThat(actualResult.getQuery()).isEqualTo(query);
        Assertions.assertThat(actualResult.getModifiedQuery()).isNull();
    }

    @Test
    @DisplayName("검색할 때, 오타 교정된 검색어가 오픈서치에 존재하면, 해당 검색어와 오타 교정된 검색어를 함께 반환한다.")
    void getFinalModifiedQueryWhenExistInOpensearch() {
        // Given
        String query = "Intreduction to Amazen Elestic MapReduce (EMR) (Korean)";
        String modifiedResult  = "Introduction to Amazon Elastic MapReduce (EMR) (Korean)";

        when(searchService.isQueryExistInOpensearch(query)).thenReturn(new SearchListRes(new ArrayList<>()));
        when(searchService.isModifiedQueryExistInOpensearch(query)).thenReturn(modifiedResult);

        // When
        SearchQueryRes actualResult = searchService.getFinalQuery(query);

        // Then
        Assertions.assertThat(actualResult.getQuery()).isEqualTo(query);
        Assertions.assertThat(actualResult.getModifiedQuery()).isEqualTo(modifiedResult);
    }


}
