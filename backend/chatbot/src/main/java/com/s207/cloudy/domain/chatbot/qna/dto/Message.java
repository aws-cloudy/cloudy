package com.s207.cloudy.domain.chatbot.qna.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
class Message {
    private final String role = "user";
    private String content;

    Message(String content) {
        this.content = content;
    }
}
