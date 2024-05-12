package com.s207.cloudy.global.auth.api;


import lombok.Getter;

@Getter
public enum Payload {
    ACCESS_HEADER("Authorization"),
    BEARER("Bearer"),
    KID("kid"),
    SUB("sub"),
    NAME("name");


    private final String data;

    Payload(String data) {
        this.data = data;
    }

}
