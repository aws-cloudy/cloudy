package com.s207.cloudy.domain.learning.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum LearningErrorCode {
    /* 500 SERVER_ERROR : 서버에러 */
    INVALID_JOB_ID("SE001", "존재하지 않는 직무 아이디입니다", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

}
