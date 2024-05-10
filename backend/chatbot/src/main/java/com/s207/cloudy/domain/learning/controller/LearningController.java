package com.s207.cloudy.domain.learning.controller;


import com.s207.cloudy.domain.chatbot.qna.dto.QuestionReq;
import com.s207.cloudy.infra.modetation.ModerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/recommend/learninig")
@RequiredArgsConstructor
public class LearningController {

    private final ModerationService moderationService;

    @GetMapping()
    public ResponseEntity<String> recommendLearning() {

        return ResponseEntity.ok("ok");


    }

    @GetMapping("/test")
    public ResponseEntity<Boolean> moderate(@RequestBody QuestionReq question){
        //부적절한 질문인지 판단
            //부적절한 질문일 경우 

        return ResponseEntity.ok(Boolean.valueOf(moderationService.isHarmful(question.getInputData())));
    }
}
