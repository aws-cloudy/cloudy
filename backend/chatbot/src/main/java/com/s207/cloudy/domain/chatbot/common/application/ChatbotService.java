package com.s207.cloudy.domain.chatbot.common.application;

import com.s207.cloudy.domain.chatbot.qna.dto.QuestionReq;
import reactor.core.publisher.Flux;

public interface ChatbotService {
    Flux<String> question(QuestionReq question);
}
