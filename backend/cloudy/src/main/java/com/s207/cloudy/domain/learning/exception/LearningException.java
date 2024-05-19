package com.s207.cloudy.domain.learning.exception;

import com.s207.cloudy.global.error.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class LearningException extends RuntimeException {


    private final ErrorCode errorCode;

    public LearningException(ErrorCode errorCode) {

            this.errorCode = errorCode;


    }


    @Override
    public String getMessage(){
        return this.errorCode.getMessage();
    }


    public HttpStatus getHttpStatus(){
        return this.errorCode.getHttpStatus();
    }


    public String getCode(){
        return this.errorCode.getCode();
    }

}
