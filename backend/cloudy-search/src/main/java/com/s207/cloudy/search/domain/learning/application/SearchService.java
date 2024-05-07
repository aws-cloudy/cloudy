package com.s207.cloudy.search.domain.learning.application;

import com.s207.cloudy.search.domain.learning.dto.SearchListRes;
import com.s207.cloudy.search.domain.learning.dto.SearchReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

public interface SearchService {
    SearchListRes getSearchAutoCompleteList(SearchReq req);
}
