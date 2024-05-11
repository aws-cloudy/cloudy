package com.s207.cloudy.domain.chatbot.common.dto;

import com.s207.cloudy.domain.chatbot.common.entity.Chat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChatRes {
    private String content;
    private Boolean isUserSent;
    private String regAt;

    public static ChatRes of(Chat chat) {
        return new ChatRes(chat.getContent(), chat.getUserSent(), chat.getRegAt());
    }
}
