package com.s207.cloudy.domain.roadmap_group.member.exception;

import com.s207.cloudy.global.error.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class MemberRoadmapException extends RuntimeException{
    private final ErrorCode errorCode;

    public MemberRoadmapException(ErrorCode errorCode) {

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