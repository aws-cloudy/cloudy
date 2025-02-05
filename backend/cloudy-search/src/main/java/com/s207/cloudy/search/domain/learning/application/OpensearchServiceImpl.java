package com.s207.cloudy.search.domain.learning.application;

import com.s207.cloudy.search.domain.learning.dto.SearchListItem;
import com.s207.cloudy.search.domain.learning.dto.SearchListRes;
import com.s207.cloudy.search.global.error.enums.ErrorCode;
import com.s207.cloudy.search.global.error.exception.OpensearchException;
import com.s207.cloudy.search.global.util.SearchResultMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.action.update.UpdateRequest;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.common.unit.Fuzziness;
import org.opensearch.common.xcontent.XContentType;
import org.opensearch.index.query.BoolQueryBuilder;
import org.opensearch.index.query.QueryBuilders;
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
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpensearchServiceImpl {

    private final RestHighLevelClient client;
    private final SearchResultMapper mapper;
    private static final String INDEX_NAME = "learning";
    private static final String FIND_FIELD = "title";

    public SearchListRes searchList(String query, int count) {
        // 요청 쿼리 생성
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        boolQuery.should(QueryBuilders.matchPhrasePrefixQuery(FIND_FIELD, query).slop(10));

        BoolQueryBuilder fuzzyBoolQuery = QueryBuilders.boolQuery();
        String[] queryTerms = query.split("\\s+"); // 공백 기준 분리
        for (String term : queryTerms) {
            fuzzyBoolQuery.must(QueryBuilders.fuzzyQuery(FIND_FIELD, term).fuzziness(Fuzziness.AUTO));
        }
        boolQuery.should(fuzzyBoolQuery);

        sourceBuilder.sort("counter", SortOrder.DESC);
        sourceBuilder.sort(SortBuilders.scoreSort().order(SortOrder.DESC));

        sourceBuilder.query(boolQuery);

        if(count != -1) {
            sourceBuilder.size(count);
        }

        // 만들어진 쿼리를 바탕으로, Opensearch에 요청
        return executeSearchAndMapResponse(new SearchRequest(INDEX_NAME).source(sourceBuilder));
    }

    public SearchListRes executeSearchAndMapResponse(SearchRequest searchRequest) {
        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            return mapper.mapSearchResponse(searchResponse);
        } catch (IOException e) {
            throw new OpensearchException(ErrorCode.OPENSEARCH_CONNECTION_ERROR);
        }
    }

    public SearchResponse executeSearchRequest(SearchRequest searchRequest) {
        try {
            return client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new OpensearchException(ErrorCode.OPENSEARCH_CONNECTION_ERROR);
        }
    }

    public SearchListRes isQueryExist(String query) {
        // 요청 쿼리 생성
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilders.matchPhrasePrefixQuery(FIND_FIELD, query));

        // 만들어진 쿼리를 바탕으로, Opensearch에 요청
        return executeSearchAndMapResponse(new SearchRequest(INDEX_NAME).source(sourceBuilder));
    }

    public void addQueryIfNotExist(SearchListRes searchResult, String query) {
        // 정확히 일치하는 검색어가 존재 여부 확인
        for (SearchListItem hit : searchResult.getSearchList()) {
            String findQuery = hit.getTitle();
            if(findQuery.equals(query)) {
                return;
            }
        }

        // 정확히 일치하는 검색어가 없으면, 해당 검색어를 Opensearch에 추가
        IndexRequest indexRequest = new IndexRequest(INDEX_NAME)
                .source(XContentType.JSON,
                        "counter", 0.01, // Hit 개수 증가
                        FIND_FIELD, query
                );

        try {
            client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new OpensearchException(ErrorCode.OPENSEARCH_CONNECTION_ERROR);
        }
    }

    public void increaseCounter(SearchListRes searchResult) {
        for (SearchListItem hit : searchResult.getSearchList()) {
            String documentId = hit.getDocumentId();

            // 해당 데이터의 Hit 개수 증가
            UpdateRequest request = new UpdateRequest(INDEX_NAME, documentId)
                    .script(
                            new Script(
                                    ScriptType.INLINE,
                                    "painless",
                                    "ctx._source.counter += params.increment",
                                    Collections.singletonMap("increment", 0.01)
                            )
                    );

            try {
                client.update(request, RequestOptions.DEFAULT);
            } catch (IOException e) {
                throw new OpensearchException(ErrorCode.OPENSEARCH_CONNECTION_ERROR);
            }
        }
    }


    public String isModifiedQueryExist(String query) {
        String[] modifiedQuery = query.split(" ");

        // 요청 쿼리 생성
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("query-suggestion", SuggestBuilders.termSuggestion(FIND_FIELD).text(query));

        sourceBuilder.suggest(suggestBuilder);

        // 만들어진 쿼리를 바탕으로, Opensearch에 요청
        SearchResponse searchResponse = executeSearchRequest(new SearchRequest(INDEX_NAME).source(sourceBuilder));
        log.info(searchResponse.toString());

        // 오타교정된 검색어가 있을 경우
        Suggest suggest = searchResponse.getSuggest();
        if (suggest != null) {
            TermSuggestion completionSuggestion = suggest.getSuggestion("query-suggestion");
            List<TermSuggestion.Entry> entries = completionSuggestion.getEntries();
            for (int i = 0; i < entries.size(); i++) {
                if (!entries.get(i).getOptions().isEmpty()) {
                    modifiedQuery[i] = entries.get(i).getOptions().get(0).getText().string();
                }
            }
        }

        return String.join(" ", modifiedQuery);
    }
}
