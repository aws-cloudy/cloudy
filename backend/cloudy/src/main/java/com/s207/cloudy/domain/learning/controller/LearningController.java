package com.s207.cloudy.domain.learning.controller;

import com.s207.cloudy.domain.learning.dto.LearningListRes;
import com.s207.cloudy.domain.learning.service.LearningService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Tag(name = "[v1] 강의 조회 API")
@RestController
@RequestMapping("/api/v1/learnings/search")
@RequiredArgsConstructor
public class LearningController {

    private final LearningService learningService;

    // 학습 전체 조회 - 검색어 오타 교정 전
    // 페이지별 + 직무별 필터링 + 난이도별 + 강의타입 + 검색
    @GetMapping
    public ResponseEntity<List<LearningListRes>> getLearningList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize,
                                                                 String[] jobName, String[] serviceName, String[] type, String[] difficulty, String query) {
        System.out.println(Arrays.toString(type));
        return ResponseEntity.ok(learningService.getLearnings(page, pageSize, jobName, serviceName, type, difficulty, query));
    }

    // 학습 전체 조회 - 검색어 오타 교정


    // 직무 관련 학습 조회


    // 학습 검색어 자동완성


}
