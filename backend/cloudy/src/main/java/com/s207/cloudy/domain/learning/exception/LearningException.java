package com.s207.cloudy.domain.learning.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LearningException extends RuntimeException {

    private final LearningErrorCode errorCode;

}
