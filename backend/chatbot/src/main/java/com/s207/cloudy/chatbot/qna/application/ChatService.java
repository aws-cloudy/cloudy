package com.s207.cloudy.chatbot.qna.application;

import java.util.Map;

public interface ChatService {
    void generateStreamingChat(String template, Map<String, Object> variables);
}
