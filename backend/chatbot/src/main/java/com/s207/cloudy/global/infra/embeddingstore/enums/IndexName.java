package com.s207.cloudy.global.infra.embeddingstore.enums;


import lombok.Getter;

@Getter
public enum IndexName {
    CLOUDY("prod"),
    QNA("qna");

    private final String name;

    IndexName(String name) {
        this.name = name;
    }
}
