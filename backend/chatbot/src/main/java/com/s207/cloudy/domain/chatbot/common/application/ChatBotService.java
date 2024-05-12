package com.s207.cloudy.domain.chatbot.common.application;

import com.s207.cloudy.domain.chatbot.common.dto.ChatListRes;
import com.s207.cloudy.domain.chatbot.common.dto.ChatReq;
import com.s207.cloudy.domain.chatbot.common.entity.Chatbot;
import reactor.core.publisher.Flux;

public interface ChatBotService {
    Flux<String> question(ChatReq question, String userId);

    ChatListRes getChatList(int type, String userId);
}
