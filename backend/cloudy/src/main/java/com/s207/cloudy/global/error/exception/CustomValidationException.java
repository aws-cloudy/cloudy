package com.s207.cloudy.global.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class CustomValidationException extends RuntimeException {

    private Map<String, Map<String, String>> errorMap;

    private HttpStatus httpStatus;

    public CustomValidationException() {}

    public CustomValidationException(String message, Map<String, Map<String, String>> errorMap) {
        super(message);
        this.errorMap = errorMap;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }


    @Override
    public String getMessage() {
        return this.errorMap.toString();
    }

}
