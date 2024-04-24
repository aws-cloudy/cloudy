package com.s207.cloudy.global.error;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

public enum ErrorCodeEnum {
    PARAMETER_ERROR("CE001", "API 요청 URL의 프로토콜, 파라미터 등에 오류가 있습니다. "),
    INVALID_PAGINATION_SIZE("CE002", "size 파라미터 값의 허용 범위가 1~100입니다."),
    INVALID_PAGINATION_PAGE("CE003", "page 파라미터 값의 허용 범위가 양수입니다."),
    SERVER_ERROR("SE001", "Internal Server Error / 데이터베이스 오류입니다.");

    @Getter
    private final String code;
    @Getter
    private final String message;

    ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorCodeEnum getEnum(String message) {
        return Arrays.stream(values())
                .filter(val -> Objects.equals(message, val.message))
                .findFirst()
                .orElse(ErrorCodeEnum.SERVER_ERROR);

    }
}
