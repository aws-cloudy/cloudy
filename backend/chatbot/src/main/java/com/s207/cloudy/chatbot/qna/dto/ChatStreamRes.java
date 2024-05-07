package com.s207.cloudy.chatbot.qna.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ChatStreamRes {
    private final List<Choice> choices = new ArrayList<>();
}
