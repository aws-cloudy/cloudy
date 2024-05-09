package com.s207.cloudy.domain.chatbot.common.application;

import com.s207.cloudy.domain.chatbot.qna.dto.QuestionReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatServiceImpl implements ChatService {


    @Override
    public Flux<String> generateChatStream(QuestionReq questionReq) {
        return null;
    }

    @Override
    public String generateChatString(QuestionReq questionReq) {
        return null;
    }
}
