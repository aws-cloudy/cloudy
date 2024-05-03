package com.s207.cloudy.domain.learning.controller;

import lombok.extern.slf4j.Slf4j;
import org.opensearch.action.get.GetRequest;
import org.opensearch.action.get.GetResponse;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.client.*;
import org.opensearch.index.query.QueryBuilders;
import org.opensearch.search.builder.SearchSourceBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/learnings/search")
public class SearchController {

    private final RestHighLevelClient client;

    public SearchController(RestHighLevelClient client) {
        this.client = client;
    }


    // 학습 검색어 자동완성
    @GetMapping("/autocomplete")
    public ResponseEntity<SearchResponse> getSearchAutoCompleteList(@RequestParam String query) throws IOException {
        // 검색 요청 구성
        SearchRequest request = new SearchRequest("learning");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("title.ngram", query));
        request.source(searchSourceBuilder);

        // Opensearch에 검색 요청 보내고 응답 받기
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

//        String str = response.toString();

        return ResponseEntity.ok(response);
    }


    // 학습 전체 조회 - 검색어 오타 교정


}
