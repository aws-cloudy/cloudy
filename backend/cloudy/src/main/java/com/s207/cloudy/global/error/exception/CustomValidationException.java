package com.s207.cloudy.global.error.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
public class CustomValidationException extends RuntimeException {

    private Map<String, Map<String, String>> errorMap;

    public CustomValidationException() {}

    public CustomValidationException(Map<String, Map<String, String>> errorMap) {
        this.errorMap = errorMap;
    }

    public CustomValidationException(String message, Map<String, Map<String, String>> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

}
