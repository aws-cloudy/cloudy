package com.s207.cloudy.global.handler.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class CustomValidationException extends RuntimeException {

    private Map<String, Map<String, String>> errorMap;

    public CustomValidationException(String message, Map<String, Map<String, String>> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

}
