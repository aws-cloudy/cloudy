package com.s207.cloudy.search.domain.learning.application;

import com.s207.cloudy.search.domain.learning.dto.SearchListItem;
import com.s207.cloudy.search.domain.learning.dto.SearchListRes;
import com.s207.cloudy.search.domain.learning.dto.SearchQueryRes;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final OpensearchServiceImpl opensearchService;
    private final RedisUtils redisUtils;
    private static final long EXPIRATION_TIME = 3*60*60*1000L; // 3 Hours

    @Override
    public SearchListRes getAutoCompleteList(SearchReq req) {
        log.info("getAutoCompleteList - Request : {}", req.toString());
        int count = req.getCount();
        String query = req.getQuery();

        // 캐시에 해당 검색어 있는지 확인
        Optional<SearchListRes> cachedResult = searchListFromCache(query);
        if (cachedResult.isPresent()) {
            log.info("getAutoCompleteList - Response From Cache : {}", cachedResult.get().toString());
            return cachedResult.get();
        }

        // 캐시에 해당 검색어 없는 경우, Opensearch에서 찾아옴
        SearchListRes searchResult = opensearchService.searchList(query, count);
        cacheSearchResult(query, searchResult);

        log.info("getAutoCompleteList - Response From Opensearch : {}", searchResult.toString());
        return searchResult;
    }

    @Override
    public SearchQueryRes getFinalQuery(String query) {
        log.info("getFinalQuery Request - : {}", query);

        SearchQueryRes res = new SearchQueryRes();
        res.setQuery(query);

        // Opensearch에서 해당 검색어의 검색결과 있는지 확인
        SearchListRes searchResult = opensearchService.isQueryExist(query);
        if(!searchResult.getSearchList().isEmpty()) {
            // 검색어가 Opensearch에 존재하지 않으면 추가
            opensearchService.addQueryIfNotExist(searchResult, query);
            // 검색결과의 Hit 개수 증가
            opensearchService.increaseCounter(searchResult);

            log.info("getFinalQuery - Response when Query is exist : {}", res);
            return res;
        }

        // Opensearch에서 오타교정된 검색어의 검색결과 있는지 확인
        String modifidedQuery = opensearchService.isModifiedQueryExist(query);
        if(!modifidedQuery.equals(query)) {
            res.setModifiedQuery(modifidedQuery);
            log.info("getFinalQuery - Response when Modified Query is exist : {}", res);
            return res;
        }

        log.info("getFinalQuery - Response : {}", res);
        return res;
    }

    public Optional<SearchListRes> searchListFromCache(String query) {
        Optional<SearchListRes> cachedResult = redisUtils.getData(query, SearchListRes.class);
//        if (cachedResult.isPresent()) {
//            redisUtils.extendExpire(query, EXPIRATION_TIME); // 캐시 유효시간 다시 초기화
//        }
        return cachedResult;
    }

    public void cacheSearchResult(String query, SearchListRes searchResult) {
        redisUtils.saveData(query, searchResult, EXPIRATION_TIME);
    }

}
