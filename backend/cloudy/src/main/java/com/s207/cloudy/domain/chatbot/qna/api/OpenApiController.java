package com.s207.cloudy.domain.chatbot.qna.api;

import com.s207.cloudy.domain.chatbot.qna.application.OpenAiService;
import com.s207.cloudy.domain.chatbot.qna.dto.QuestionReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OpenApiController {
    private final OpenAiService openAiService;

    @PostMapping(value = "chatbot", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public String flux(@RequestBody QuestionReq question) {
        return openAiService.ask(question.getQuestion());
    }

}
