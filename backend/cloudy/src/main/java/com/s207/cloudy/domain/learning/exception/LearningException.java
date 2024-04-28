package com.s207.cloudy.domain.learning.exception;

import com.s207.cloudy.global.error.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
public class LearningException extends RuntimeException {


    private final ErrorCode errorCode;

    public LearningException(ErrorCode errorCode) {

            this.errorCode = errorCode;


    }

}
