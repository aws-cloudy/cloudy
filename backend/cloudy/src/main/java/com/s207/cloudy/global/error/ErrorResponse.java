package com.s207.cloudy.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
public class ErrorResponse {

    private String code;

    private String message;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}