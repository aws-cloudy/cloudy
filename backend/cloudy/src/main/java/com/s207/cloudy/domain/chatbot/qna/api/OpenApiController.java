package com.s207.cloudy.domain.chatbot.qna.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.s207.cloudy.domain.chatbot.qna.application.OpenAiService;
import com.s207.cloudy.domain.chatbot.qna.application.SseEmitterService;
import com.s207.cloudy.domain.chatbot.qna.dto.QuestionReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OpenApiController {
    private final OpenAiService openAiService;
    private final SseEmitterService sseEmitterService;

    @PostMapping(value = "flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux(@RequestBody QuestionReq question) {
        try {
            return openAiService.ask(question.getQuestion());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return Flux.empty();
        }
    }

//    @GetMapping(path = "/sse/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public ResponseEntity<SseEmitter> subscribe() {
//        String sseId = UUID.randomUUID().toString();
//        SseEmitter emitter = sseEmitterService.subscribe(sseId);
//        return ResponseEntity.ok(emitter);
//    }

    //eventPayload 를 SSE 로 연결된 모든 클라이언트에게 broadcasting 한다.
    @PostMapping(path = "sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Void> broadcast(@RequestBody QuestionReq question) throws JsonProcessingException {
        String sseId = UUID.randomUUID().toString();
        String emitterId = sseEmitterService.subscribe(sseId);
        sseEmitterService.ask(emitterId, question);
        return ResponseEntity.ok().build();
    }
}
