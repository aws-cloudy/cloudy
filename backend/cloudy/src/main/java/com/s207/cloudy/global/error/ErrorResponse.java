package com.s207.cloudy.global.error;

import com.s207.cloudy.global.error.enums.ErrorCodeEnum;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private String code;

    private String message;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse of(ErrorCodeEnum errorCodeEnum) {
        return new ErrorResponse(errorCodeEnum.getCode(), errorCodeEnum.getMessage());
    }

}