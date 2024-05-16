package com.s207.cloudy.domain.members.exception;

import com.s207.cloudy.global.error.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class MemberException extends RuntimeException{
    private final ErrorCode errorCode;


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
