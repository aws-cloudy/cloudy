package com.s207.cloudy.domain.learning.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
public class LearningException extends RuntimeException {

    private Map<String, String> error;
    private HttpStatus status;

    public LearningException(LearningErrorCode errorCode) {
        error = new HashMap<>();
        error.put("code", errorCode.getCode());
        error.put("message", errorCode.getMessage());

        this.status = errorCode.getHttpStatus();
    }

}
