package com.s207.cloudy.global.error;

import com.s207.cloudy.domain.learning.exception.LearningException;
import com.s207.cloudy.global.error.exception.InvalidPaginationArgumentException;
import com.s207.cloudy.global.error.exception.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public ErrorResponse validationExceptionHandler(CustomValidationException e) {
        log.error(e.getMessage());
        return ErrorResponse.builder(e,HttpStatus.BAD_REQUEST, e.getErrorMap().toString() ).build();
    }

    @ExceptionHandler(LearningException.class)
    public ErrorResponse learningExceptionHandler(LearningException e){
        log.error(e.getMessage());
        return ErrorResponse.builder(e, e.getErrorCode().getHttpStatus(), e.getMessage()).build();
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<ErrorResponse> serverErrorExceptionHandler(Exception e) {
//        return new ResponseEntity<ErrorResponse>(new ErrorResponse("SE001", "Internal Server Error입니다."), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler({InvalidPaginationArgumentException.class})
    public ErrorResponse badRequestException400(InvalidPaginationArgumentException e) {
        return ErrorResponse.builder(e, e.getErrorCode().getHttpStatus(), e.getMessage()).build();
    }

}
