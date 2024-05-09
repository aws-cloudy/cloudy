package com.s207.cloudy.search.domain.learning.application;

import com.s207.cloudy.search.domain.learning.dto.SearchListItem;
import com.s207.cloudy.search.domain.learning.dto.SearchListRes;
import com.s207.cloudy.search.domain.learning.dto.SearchReq;
import com.s207.cloudy.search.global.error.enums.ErrorCode;
import com.s207.cloudy.search.global.error.exception.OpensearchException;
import com.s207.cloudy.search.global.util.RedisUtils;
import com.s207.cloudy.search.global.util.SearchResultMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.action.update.UpdateRequest;
import org.opensearch.action.update.UpdateResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.common.unit.Fuzziness;
import org.opensearch.common.xcontent.XContentType;
import org.opensearch.index.query.*;
import org.opensearch.script.Script;
import org.opensearch.script.ScriptType;
import org.opensearch.search.builder.SearchSourceBuilder;
import org.opensearch.search.sort.SortBuilders;
import org.opensearch.search.sort.SortOrder;
import org.opensearch.search.suggest.Suggest;
import org.opensearch.search.suggest.SuggestBuilder;
import org.opensearch.search.suggest.SuggestBuilders;
import org.opensearch.search.suggest.term.TermSuggestion;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        if(IsQueryExist(query)) {
            return query;
        }

        // 일치하는 검색어 없다면, 오타교정된 검색어 있는지 확인
        return ModifiedQueryIfExist(query);
    }

    private String ModifiedQueryIfExist(String query) {
        String[] modifiedQuery = query.split(" ");

        // SearchRequest 생성
        SearchRequest searchRequest = new SearchRequest("test");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // Suggest 쿼리 생성
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("query-suggestion", SuggestBuilders.termSuggestion("title").text(query));

        searchSourceBuilder.suggest(suggestBuilder);
        searchRequest.source(searchSourceBuilder);

        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            // 검색 결과 처리
            Suggest suggest = searchResponse.getSuggest();
            if (suggest != null) {
                TermSuggestion completionSuggestion = suggest.getSuggestion("query-suggestion");
                List<TermSuggestion.Entry> entries = completionSuggestion.getEntries();
                for (int i=0; i<entries.size(); i++) {
                    if(entries.get(i).getOptions().size() == 0) {
                        continue;
                    }
                    modifiedQuery[i] = entries.get(i).getOptions().get(0).getText().string();
                }
            }

            return String.join(" ", modifiedQuery);
        } catch (IOException e) {
            // Handle connection errors with Opensearch
            throw new OpensearchException(ErrorCode.OPENSEARCH_CONNECTION_ERROR);
        }

    }

    private boolean IsQueryExist(String query) {
        // Construct the search request
        SearchRequest searchRequest = new SearchRequest("test");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        // Match phrase prefix query
        sourceBuilder.query(QueryBuilders.matchPhrasePrefixQuery("title", query));

        searchRequest.source(sourceBuilder);

        try {
            // Execute the search request
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            
            // Map the search response to a SearchListRes object
            SearchListRes response = mapper.mapSearchResponse(searchResponse);

            if (response.getSearchList().size() > 0) {
                addDocumentToOpensearch(response, query);
                incrementCounterAndAddDocument(response, query);

                return true;
            }

        } catch (IOException e) {
            // Handle connection errors with Opensearch
            throw new OpensearchException(ErrorCode.OPENSEARCH_CONNECTION_ERROR);
        }

        return false;
    }

    private boolean addDocumentToOpensearch(SearchListRes response, String query) {
        for (SearchListItem hit : response.getSearchList()) {
            String findQuery = hit.getTitle();
            if(findQuery.equals(query)) {
                return false;
            }
        }

        // 같은 쿼리를 가진 문서가 없으면 새로운 문서를 추가
        IndexRequest indexRequest = new IndexRequest("test")
                .source(XContentType.JSON,
                        "counter", 0.01,
                        "title", query
                );

        try {
            client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            // Opensearch 연결 오류를 처리
            throw new OpensearchException(ErrorCode.OPENSEARCH_CONNECTION_ERROR);
        }

        return true;
    }

    private void incrementCounterAndAddDocument(SearchListRes response, String query) {
        for (SearchListItem hit : response.getSearchList()) {
            String documentId = String.valueOf(hit.getDocumentId());

            // Construct the update reques
            UpdateRequest request = new UpdateRequest("test", documentId)
                    .script(
                            new Script(
                                    ScriptType.INLINE,
                                    "painless",
                                    "ctx._source.counter += params.increment",
                                    Collections.singletonMap("increment", 0.01)
                            )
                    );

            try {
                // Execute the update request
                client.update(request, RequestOptions.DEFAULT);
            } catch (IOException e) {
                // Handle connection errors with Opensearch
                throw new OpensearchException(ErrorCode.OPENSEARCH_CONNECTION_ERROR);
            }
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
            fuzzyBoolQuery.must(QueryBuilders.fuzzyQuery("title", term).fuzziness(Fuzziness.AUTO));
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
