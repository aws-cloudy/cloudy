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

    @Override
    public SearchListRes getSearchAutoCompleteList(SearchReq req) {
        String query = req.getQuery();

        // Check if the search result is cached in Redis
        Optional<SearchListRes> cachedResult = redisUtils.getData(query, SearchListRes.class);
        if (cachedResult.isPresent()) {
            redisUtils.extendExpire(query, 10800000L); // 3 Hours
            return cachedResult.get();
        }

        // Perform a search using Opensearch if the result is not cached in Redis
        SearchListRes searchResult = performOpensearch(query);

        // Cache the search result in Redis
        redisUtils.saveData(query, searchResult, 10800000L);

        return searchResult;
    }

    private SearchListRes performOpensearch(String query) {
        // Construct the search request
        SearchRequest searchRequest = new SearchRequest("learning");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        // Match phrase prefix query
        boolQuery.should(QueryBuilders.matchPhrasePrefixQuery("title", query));

        // Fuzzy query
        boolQuery.should(QueryBuilders.fuzzyQuery("title", query).fuzziness(Fuzziness.AUTO));

        sourceBuilder.query(boolQuery);
        sourceBuilder.size(5);
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
