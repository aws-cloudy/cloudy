package com.s207.cloudy.search.domain.learning.application;

import com.s207.cloudy.search.domain.learning.dto.SearchListRes;
import com.s207.cloudy.search.domain.learning.dto.SearchReq;
import com.s207.cloudy.search.global.error.enums.ErrorCode;
import com.s207.cloudy.search.global.error.exception.OpensearchException;
import com.s207.cloudy.search.global.util.RedisUtils;
import com.s207.cloudy.search.global.util.SearchResultMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.common.unit.Fuzziness;
import org.opensearch.index.query.*;
import org.opensearch.search.builder.SearchSourceBuilder;
import org.opensearch.search.sort.SortBuilders;
import org.opensearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{

    private final RestHighLevelClient client;
    private final SearchResultMapper mapper;
    private final RedisUtils redisUtils;
    private static final long EXPIRATION_TIME = (long)3*60*60*1000; // 3 Hours

    @Override
    public SearchListRes getSearchAutoCompleteList(SearchReq req) {
        String query = req.getQuery();

        // Check if the search result is cached in Redis
        Optional<SearchListRes> cachedResult = redisUtils.getData(query, SearchListRes.class);
        if (cachedResult.isPresent()) {
            redisUtils.extendExpire(query, EXPIRATION_TIME);
            return cachedResult.get();
        }

        // Perform a search using Opensearch if the result is not cached in Redis
        SearchListRes searchResult = searchAutoCompleteList(query, 5);

        // Cache the search result in Redis
        redisUtils.saveData(query, searchResult, EXPIRATION_TIME);

        return searchResult;
    }

    @Override
    public String getFinalQuery(String query) {
        // 일치하는 검색어 있는지 확인
        if(searchIsExistQuery(query, 1)) {
            return query; // 검색어 그대로 리턴
        }

        // 일치하는 검색어 없다면, 오타교정된 검색어 있는지 확인


        return query;
    }

    private boolean searchIsExistQuery(String query, int size) {
        // Construct the search request
        SearchRequest searchRequest = new SearchRequest("test");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        // Match phrase prefix query
        sourceBuilder.query(QueryBuilders.matchPhrasePrefixQuery("title", query));

        sourceBuilder.size(size);
        searchRequest.source(sourceBuilder);

        try {
            // Execute the search request
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            
            // Map the search response to a SearchListRes object
            SearchListRes response = mapper.mapSearchResponse(searchResponse);
            return response.getSearchList().size()>0 ? true : false;
        } catch (IOException e) {
            // Handle connection errors with Opensearch
            throw new OpensearchException(ErrorCode.OPENSEARCH_CONNECTION_ERROR);
        }
    }

    private SearchListRes searchAutoCompleteList(String query, int size) {
        // Construct the search request
        SearchRequest searchRequest = new SearchRequest("test");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        // Match phrase prefix query
        boolQuery.should(QueryBuilders.matchPhrasePrefixQuery("title", query).slop(10));

        // Fuzzy query
        BoolQueryBuilder fuzzyBoolQuery = QueryBuilders.boolQuery();
        String[] queryTerms = query.split("\\s+");
        for (String term : queryTerms) {
            fuzzyBoolQuery.should(QueryBuilders.fuzzyQuery("title", term).fuzziness(Fuzziness.AUTO));
        }
        boolQuery.should(fuzzyBoolQuery);

        // Multiple sort options
        sourceBuilder.sort("counter", SortOrder.DESC);
        sourceBuilder.sort(SortBuilders.scoreSort().order(SortOrder.DESC));

        sourceBuilder.query(boolQuery);
        sourceBuilder.size(size);
        searchRequest.source(sourceBuilder);

        try {
            // Execute the search request
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            // Map the search response to a SearchListRes object
            return mapper.mapSearchResponse(searchResponse);
        } catch (IOException e) {
            // Handle connection errors with Opensearch
            throw new OpensearchException(ErrorCode.OPENSEARCH_CONNECTION_ERROR);
        }
    }

}
