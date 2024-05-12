package com.s207.cloudy.global.infra.embeddingstore.enums;

import lombok.Getter;

@Getter
public enum MetadataKey {
    ID("id"),
    KEYWORDS("keywords"),
    ANSWER("ans"),
    DOCS("docs");


    private final String name;

    MetadataKey(String data) {
        this.name = data;
    }
}
