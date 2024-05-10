package com.s207.cloudy.search.global.error.exception;

import com.s207.cloudy.search.global.error.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RedisException extends RuntimeException {

    private final ErrorCode errorCode;

    public RedisException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }


    @Override
    public String getMessage() {
        return this.errorCode.getMessage();
    }


    public HttpStatus getHttpStatus(){
        return this.errorCode.getHttpStatus();
    }

    public String getCode(){
        return this.errorCode.getCode();
    }
}
