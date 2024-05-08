package com.s207.cloudy.domain.chatbot.qna.api;

import com.s207.cloudy.domain.chatbot.qna.application.QnaService;
import com.s207.cloudy.domain.chatbot.qna.dto.QuestionReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OpenApiController {
    private final QnaService qnaService;

    @PostMapping(value = "chatbot",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public Flux<String> returnFluxChat(@RequestBody QuestionReq question) {
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
