package com.s207.cloudy.domain.chatbot.entity;

import lombok.Getter;

@Getter
public enum Chatbot {

    QNA(0),

    LEARNING(1),

    FEE(2),

    ROADMAP(3);

    private final int num;

    Chatbot(int num) {
        this.num = num;
    }
}
