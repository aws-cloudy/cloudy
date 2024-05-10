package com.s207.cloudy.global.infra.chatmodel;

import reactor.core.publisher.Flux;

import java.util.Map;

public interface ChatService {
    Flux<String> generateStreamingChat(String template, Map<String, Object> variables, String userId);

    String generateChat(String template, Map<String, Object> variables);
}
