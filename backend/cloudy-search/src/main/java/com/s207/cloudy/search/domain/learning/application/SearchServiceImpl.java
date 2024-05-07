package com.s207.cloudy.search.domain.learning.application;

import com.s207.cloudy.search.domain.learning.dto.SearchListRes;
import com.s207.cloudy.search.domain.learning.dto.SearchReq;
import com.s207.cloudy.search.global.error.enums.ErrorCode;
import com.s207.cloudy.search.global.error.exception.OpensearchException;
import com.s207.cloudy.search.global.util.SearchResultMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.common.unit.Fuzziness;
import org.opensearch.index.query.*;
import org.opensearch.search.SearchHit;
import org.opensearch.search.builder.SearchSourceBuilder;
import org.opensearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{

    private final RestHighLevelClient client;
    private final SearchResultMapper mapper;

    @Override
    public SearchListRes getSearchAutoCompleteList(SearchReq req) {
        String query = req.getQuery();

        // 검색 요청 구성
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
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            // SearchResponse로부터 검색 결과를 매핑하여 반환
            return  mapper.mapSearchResponse(searchResponse, query);
        } catch (IOException e) {
            throw new OpensearchException(ErrorCode.OPENSEARCH_CONNECTION_ERROR);
        }

    }


}
