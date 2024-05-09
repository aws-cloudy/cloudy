package com.s207.cloudy.domain.chatbot.common.application;

import com.s207.cloudy.domain.chatbot.entity.Chatbot;
import com.s207.cloudy.domain.chatbot.qna.application.QnaService;
import com.s207.cloudy.domain.chatbot.qna.dto.QuestionReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ChatbotServiceImpl implements ChatbotService {
    private final QnaService qnaService;
    private final ChatQueryService chatQueryService;

    @Override
    public Flux<String> question(QuestionReq question) {

        String type = question.getType();
        if (Chatbot.QNA.name().equals(type)) {
            chatQueryService.saveChat("userIdTest", Chatbot.QNA,
                question.getInputData(), true);
            return qnaService.generateChatStream(question);
        }

        return null;
    }
}
