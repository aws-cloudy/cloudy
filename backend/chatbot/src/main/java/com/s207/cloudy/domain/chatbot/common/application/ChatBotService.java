package com.s207.cloudy.domain.chatbot.common.application;

import com.s207.cloudy.domain.chatbot.common.dto.ChatListRes;
import com.s207.cloudy.domain.chatbot.common.dto.QuestionReq;
import reactor.core.publisher.Flux;

public interface ChatBotService {
    Flux<String> question(QuestionReq question, String userId);

    ChatListRes getChatList(int type, String userId);
}
