package com.s207.cloudy.search.domain.learning.api;

import com.s207.cloudy.search.domain.learning.application.SearchService;
import com.s207.cloudy.search.domain.learning.dto.SearchListRes;
import com.s207.cloudy.search.domain.learning.dto.SearchReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/learnings/search")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SearchController {

    private final SearchService searchService;

    // 학습 검색어 자동완성
    @PostMapping("/autocomplete")
    public ResponseEntity<SearchListRes> getSearchAutoCompleteList(@RequestBody SearchReq req) {
        return ResponseEntity.ok(searchService.getSearchAutoCompleteList(req));
    }


    // 학습 전체 조회 - 검색어 오타 교정


}