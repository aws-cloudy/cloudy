package com.s207.cloudy.global.error.exception;

import com.s207.cloudy.global.error.enums.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidPaginationArgumentException extends RuntimeException{
    private final ErrorCode errorCode;

    public InvalidPaginationArgumentException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }


}
