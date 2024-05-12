package com.s207.cloudy.global.infra.embeddingstore.enums;


import lombok.Getter;

@Getter
public enum NameSpace {
    QUESTION("question"),
    LEARNING("learning"),
    RATE("rate"),
    ROADMAP("roadmap");

    private final String name;

    NameSpace(String data) {
        this.name = data;
    }
}
