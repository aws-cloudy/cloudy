package com.s207.cloudy.infra.chat;

import reactor.core.publisher.Flux;

import java.util.Map;

public interface ChatService {
    Flux<String> generateStreamingChat(String template, Map<String, Object> variables);
    String generateChat(String template, Map<String, Object> variables);
}
