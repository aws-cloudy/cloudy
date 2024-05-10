package com.s207.cloudy.domain.chatbot.common.dto;

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
}
