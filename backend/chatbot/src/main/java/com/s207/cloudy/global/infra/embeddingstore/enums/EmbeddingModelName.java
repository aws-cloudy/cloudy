package com.s207.cloudy.global.infra.embeddingstore.enums;

import lombok.Getter;

@Getter
public enum EmbeddingModelName {


    TEXT_EMBEDDING_3_SMALL("text-embedding-3-small");



    private final String name;


    EmbeddingModelName(String name) {
        this.name = name;
    }
}
