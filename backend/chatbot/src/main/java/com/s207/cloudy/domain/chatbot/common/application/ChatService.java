package com.s207.cloudy.domain.chatbot.common.application;


import com.s207.cloudy.domain.chatbot.common.dto.ChatCreateReq;
import com.s207.cloudy.domain.chatbot.common.dto.ChatRes;

public interface ChatService {
    ChatRes createChat(ChatCreateReq chatReq);
}
