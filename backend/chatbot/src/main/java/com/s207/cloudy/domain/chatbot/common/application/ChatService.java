package com.s207.cloudy.domain.chatbot.common.application;


import com.s207.cloudy.domain.chatbot.common.dto.QuestionReq;
import reactor.core.publisher.Flux;

public interface ChatService {
    Flux<String> generateChatStream(QuestionReq questionReq);

    String generateChatString(QuestionReq questionReq);
}
