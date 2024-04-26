package com.s207.cloudy.domain.learning.controller;

import com.s207.cloudy.domain.learning.dto.LearningListRes;
import com.s207.cloudy.domain.learning.dto.LearningSearchByJobReq;
import com.s207.cloudy.domain.learning.dto.LearningSearchReq;
import com.s207.cloudy.domain.learning.service.LearningService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[v1] 강의 조회 API")
@RestController
@RequestMapping("/api/v1/learnings/search")
@RequiredArgsConstructor
public class LearningController {

    private final LearningService learningService;

    // 학습 전체 조회 - 검색어 오타 교정 전 (페이지별 + 직무별 + 서비스명별 + 난이도별 + 강의분류별 + 검색 필터링)
    @GetMapping
    public ResponseEntity<LearningListRes> getLearningList(@Valid LearningSearchReq learningSearchReq, BindingResult bindingResult) {
        return ResponseEntity.ok(learningService.getLearnings(learningSearchReq));
    }

    // 직무 관련 학습 조회 (로그인시)
    @GetMapping("/job/{jobId}")
    public ResponseEntity<LearningListRes> getLearningListByJob(@PathVariable int jobId, @Valid LearningSearchByJobReq req, BindingResult bindingResult) {
        return ResponseEntity.ok(learningService.getLearningsByJob(jobId, req.getCount()));
    }

    // 직무 관련 학습 조회 (로그아웃시)
    @GetMapping("/job")
    public ResponseEntity<LearningListRes> getLearningListByJob(@Valid LearningSearchByJobReq req, BindingResult bindingResult) {
        return ResponseEntity.ok(learningService.getLearningsByJob(req.getCount()));
    }

    // 학습 전체 조회 - 검색어 오타 교정


    // 학습 검색어 자동완성


}
