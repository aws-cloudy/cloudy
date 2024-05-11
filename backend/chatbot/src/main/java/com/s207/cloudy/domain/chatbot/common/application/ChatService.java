package com.s207.cloudy.domain.chatbot.common.application;


import com.s207.cloudy.domain.chatbot.common.dto.ChatReq;
import reactor.core.publisher.Flux;

public interface ChatService {
    Flux<String> generateChatStream(ChatReq chatReq, String userId);

    String generateChatString(ChatReq chatReq);
}
