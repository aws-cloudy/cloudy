package com.s207.cloudy.domain.chatbot.common.application;

import com.s207.cloudy.domain.chatbot.qna.dto.QuestionReq;
import reactor.core.publisher.Flux;

public interface ChatbotService {
    public Flux<String> question(QuestionReq question);
}
