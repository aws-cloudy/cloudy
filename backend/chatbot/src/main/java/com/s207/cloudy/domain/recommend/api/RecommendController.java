package com.s207.cloudy.domain.recommend.api;


import com.s207.cloudy.domain.members.entity.Member;
import com.s207.cloudy.domain.recommend.learning.application.LearningServiceImpl;
import com.s207.cloudy.domain.recommend.learning.dto.LearningListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recommend")
public class RecommendController {

    private final LearningServiceImpl learningService;

    @GetMapping("/learning")
    public ResponseEntity<LearningListRes> recommendLearning(
            @RequestParam("query") String query,
            @RequestParam("num") Integer num,
            @AuthenticationPrincipal Member member
    ){
        return ResponseEntity.ok(learningService.recommendLearning(query, num));
    }


}
