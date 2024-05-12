package com.s207.cloudy.global.infra.modertation;

import com.s207.cloudy.global.error.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class ModerationException extends RuntimeException{
    private ErrorCode errorCode;

    public ModerationException(ErrorCode errorCode) {
        this.errorCode = errorCode;

    }


    public HttpStatus getHttpStatus(){
        return this.errorCode.getHttpStatus();
    }

    @Override
    public String getMessage(){
        return this.errorCode.getMessage();
    }

    public String getCode(){
        return this.errorCode.getCode();
    }

}
