package com.s207.cloudy.domain.chatbot.common.application;

import com.s207.cloudy.domain.chatbot.entity.Chat;
import com.s207.cloudy.domain.chatbot.entity.Chatbot;

/**
 * @author 이하늬
 * @since 1.0
 */
public interface ChatQueryService {
    Chat saveChat(String userId, Chatbot chatbot, String content, boolean isUserSent);
}
