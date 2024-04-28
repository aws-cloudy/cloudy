package com.s207.cloudy.global.error.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Objects;
@Getter
public enum ErrorCode {
    PARAMETER_ERROR("CE001", "API 요청 URL의 프로토콜, 파라미터 등에 오류가 있습니다. ", HttpStatus.BAD_REQUEST),
    INVALID_PAGINATION_SIZE("CE002", "size 파라미터 값의 허용 범위가 1~100입니다.", HttpStatus.BAD_REQUEST),
    INVALID_PAGINATION_PAGE("CE003", "page 파라미터 값의 허용 범위가 양수입니다.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("CE004", "로그인이 필요합니다.", HttpStatus.UNAUTHORIZED),
    SERVER_ERROR("SE001", "Internal Server Error / 데이터베이스 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_JOB_ID("SE002", "존재하지 않는 직무 아이디입니다", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;

    private final String message;

    private final HttpStatus httpStatus;



    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public static ErrorCode getEnum(String message) {
        return Arrays.stream(values())
                .filter(val -> Objects.equals(message, val.message))
                .findFirst()
                .orElse(ErrorCode.SERVER_ERROR);

    }
}
