package com.s207.cloudy.search.domain.learning.api;

import com.s207.cloudy.search.domain.learning.application.SearchService;
import com.s207.cloudy.search.domain.learning.dto.SearchListRes;
import com.s207.cloudy.search.domain.learning.dto.SearchReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    // 학습 검색어 오타가 있으면 교정된 결과
    @GetMapping("/final")
    public ResponseEntity<String> getFinalQuery(@RequestParam String query) {
        return ResponseEntity.ok(searchService.getFinalQuery(query));
    }

}
