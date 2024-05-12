package com.s207.cloudy.domain.recommend.exception;

import com.s207.cloudy.global.error.enums.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class RecommendException extends RuntimeException{


    private ErrorCode errorCode;


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
