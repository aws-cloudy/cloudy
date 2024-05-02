package com.s207.cloudy.domain.chatbot.qna.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.s207.cloudy.domain.chatbot.qna.dto.CompletionReq;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.core.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class OpenAiService {
    @Value("${openai.url}")
    private String openAiUrl;

    @Value("${openai.key}")
    private String openAiKey;

    private WebClient client;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    @PostConstruct
    public void init() {
        client = WebClient.builder()
                .baseUrl(openAiUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(AUTHORIZATION, BEARER + openAiKey)
                .build();
    }

    public Flux<String> ask(String question) throws JsonProcessingException {
        CompletionReq request = new CompletionReq(question);
        String requestValue = objectMapper.writeValueAsString(request);

        Flux<String> eventStream = client.post()
                .bodyValue(requestValue)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(String.class);

        return eventStream;

    }

}
