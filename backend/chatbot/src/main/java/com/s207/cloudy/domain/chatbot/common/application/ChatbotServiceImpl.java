package com.s207.cloudy.domain.chatbot.common.application;

import com.s207.cloudy.domain.chatbot.domain.Chatbot;
import com.s207.cloudy.domain.chatbot.qna.application.QnaService;
import com.s207.cloudy.domain.chatbot.qna.dto.QuestionReq;
import com.s207.cloudy.global.infra.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ChatbotServiceImpl implements ChatbotService {
    private final QnaService qnaService;

    @Override
    public Flux<String> question(QuestionReq question) {

        String type = question.getType();
        if (Chatbot.QNA.name().equals(type)) {
            return qnaService.generateChatStream(question);
        }

        return null;
    }
}
