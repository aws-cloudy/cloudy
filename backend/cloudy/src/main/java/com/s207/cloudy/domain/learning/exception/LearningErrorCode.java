package com.s207.cloudy.domain.learning.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum LearningErrorCode {
    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_DIFFICULTY("CE001", "API 요청 URL의 난이도 파라미터가 유효하지 않습니다.", HttpStatus.BAD_REQUEST),
    INVALID_TYPE("CE001", "API 요청 URL의 강의 분류 파라미터가 유효하지 않습니다.", HttpStatus.BAD_REQUEST),
    INVALID_JOBNAME("CE001", "API 요청 URL의 직무명 파라미터가 유효하지 않습니다.", HttpStatus.BAD_REQUEST),
    INVALID_SERVICENAME("CE001", "API 요청 URL의 서비스명 파라미터가 유효하지 않습니다.", HttpStatus.BAD_REQUEST),
    INVALID_PAGE_SIZE("CE002", "size 파라미터 값은 1이상 100이하입니다.", HttpStatus.BAD_REQUEST),
    INVALID_PAGE("CE003", "page 파라미터 값은 양수여야 합니다.", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
