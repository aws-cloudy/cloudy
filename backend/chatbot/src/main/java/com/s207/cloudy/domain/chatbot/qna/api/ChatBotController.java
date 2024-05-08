package com.s207.cloudy.domain.chatbot.qna.api;

import static org.springframework.http.HttpStatus.OK;

import com.s207.cloudy.domain.chatbot.qna.application.QnaService;
import com.s207.cloudy.domain.chatbot.qna.dto.QuestionReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/chatbot")
public class ChatBotController {
    private final QnaService qnaService;

    @PostMapping(value = "/qna",
        produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public Flux<String> qnaChat(@RequestBody QuestionReq question) {
        log.info("컨트롤러 입력");
        return qnaService.generateChat(question);
    }


    @PostMapping(value = "chatbot2")
    public ResponseEntity<String> eturnChat(@RequestBody QuestionReq question) {
        log.info("컨트롤러 입력");
        return ResponseEntity
            .status(OK)
            .body(qnaService.generateChat2(question));
    }
}
