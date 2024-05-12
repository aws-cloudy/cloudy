package com.s207.cloudy.global.infra.chatmodel;

import com.s207.cloudy.domain.chatbot.common.entity.Chatbot;
import reactor.core.publisher.Flux;

import java.util.Map;

public interface ChatService {

    String extractKeywords(String query);
    Flux<String> generateStreamingChat(String template, Map<String, Object> variables, String userId, Chatbot chatbot);

    String generateChat(String template, Map<String, Object> variables);
}
