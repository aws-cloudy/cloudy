package com.s207.cloudy.domain.chatbot.qna.api;

import com.s207.cloudy.domain.chatbot.qna.application.QnaService;
import com.s207.cloudy.domain.chatbot.qna.dto.QuestionReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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
        return qnaService.generateChat(question);
    }

}
