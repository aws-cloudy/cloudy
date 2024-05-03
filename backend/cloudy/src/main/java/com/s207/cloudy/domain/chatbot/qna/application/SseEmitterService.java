package com.s207.cloudy.domain.chatbot.qna.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.s207.cloudy.domain.chatbot.qna.dto.ChatStreamRes;
import com.s207.cloudy.domain.chatbot.qna.dto.CompletionReq;
import com.s207.cloudy.domain.chatbot.qna.dto.QuestionReq;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.core.HttpHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class SseEmitterService {
    @Value("${openai.url}")
    private String openAiUrl;

    @Value("${openai.key}")
    private String openAiKey;
    private static final long TIMEOUT = 60 * 1000;
    private static final long RECONNECTION_TIMEOUT = 1000L;
    private final Map<String, SseEmitter> emitterMap = new ConcurrentHashMap<>();
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private WebClient client;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    @PostConstruct
    public void init() {
        client = WebClient.builder()
                .baseUrl(openAiUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(AUTHORIZATION, BEARER + openAiKey)
                .build();
    }

    public String subscribe(String id) {
        SseEmitter emitter = createEmitter();
        //연결 세션 timeout 이벤트 핸들러 등록
        emitter.onTimeout(() -> {
            log.info("server sent event timed out : id={}", id);
            //onCompletion 핸들러 호출
            emitter.complete();
        });

        //에러 핸들러 등록
        emitter.onError(e -> {
            log.info("server sent event error occurred : id={}, message={}", id, e.getMessage());
            //onCompletion 핸들러 호출
            emitter.complete();
        });

        //SSE complete 핸들러 등록
        emitter.onCompletion(() -> {
            if (emitterMap.remove(id) != null) {
                log.info("server sent event removed in emitter cache: id={}", id);
            }

            log.info("disconnected by completed server sent event: id={}", id);
        });

        emitterMap.put(id, emitter);

//        //초기 연결시에 응답 데이터를 전송할 수도 있다.
//        try {
//            SseEmitter.SseEventBuilder event = SseEmitter.event()
//                    //event 명 (event: event example)
//                    .name("event example")
//                    //event id (id: id-1) - 재연결시 클라이언트에서 `Last-Event-ID` 헤더에 마지막 event id 를 설정
//                    .id(String.valueOf("id-1"))
//                    //event data payload (data: SSE connected)
//                    .data("SSE connected")
//                    //SSE 연결이 끊어진 경우 재접속 하기까지 대기 시간 (retry: <RECONNECTION_TIMEOUT>)
//                    .reconnectTime(RECONNECTION_TIMEOUT);
//            emitter.send(event);
//        } catch (IOException e) {
//            log.error("failure send media position data, id={}, {}", id, e.getMessage());
//        }
        return id;
    }

    public void ask(String id, QuestionReq question) throws JsonProcessingException {
        SseEmitter emitter = emitterMap.get(id);
        getRetrieve(emitter, question.getQuestion());
    }

    public void getRetrieve(SseEmitter emitter, String question) throws JsonProcessingException {
        CompletionReq request = new CompletionReq(question);
        String requestValue = objectMapper.writeValueAsString(request);
        client.post()
                .bodyValue(requestValue)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchangeToFlux(response -> response.bodyToFlux(ChatStreamRes.class))
                .doOnNext(data -> {
                    try {
                        if (data.getChoices().get(0).getFinishReason() != null) {
                            emitter.complete();
                            return;
                        }
                        emitter.send(data.getChoices().get(0).getDelta().getContent());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .doOnError(emitter::completeWithError)
                .doOnComplete(emitter::complete)
                .subscribe();
    }

    private SseEmitter createEmitter() {
        return new SseEmitter(TIMEOUT);
    }
}
