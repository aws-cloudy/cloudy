package com.s207.cloudy.global.error;

import com.s207.cloudy.domain.learning.exception.LearningException;
import com.s207.cloudy.global.error.exception.InvalidPaginationArgumentException;
import com.s207.cloudy.global.error.exception.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<CommonErrorResponse> validationExceptionHandler(CustomValidationException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(
                    CommonErrorResponse
                        .builder()
                        .errorMap(e.getErrorMap())
                        .build()
                    );


        // return ErrorResponse.builder(e,HttpStatus.BAD_REQUEST, e.getErrorMap().toString() ).build();
    }

    @ExceptionHandler(LearningException.class)
    public ResponseEntity<CommonErrorResponse> learningExceptionHandler(LearningException e){
        log.error("Exception type : {}, message :{}",e.getClass(),e.getMessage());



        return ResponseEntity
                .status(e.getHttpStatus())
                .body(CommonErrorResponse
                        .builder()
                        .code(e.getErrorCode().getCode())
                        .message(e.getErrorCode().getMessage())
                        .build()
                );

        //return ErrorResponse.builder(e, e.getErrorCode().getHttpStatus(), e.getMessage()).build();
    }


    @ExceptionHandler({InvalidPaginationArgumentException.class})
    public ResponseEntity<CommonErrorResponse> badRequestException400(InvalidPaginationArgumentException e) {
        log.error("Exception type : {}, message :{}",e.getClass(),e.getMessage());
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(CommonErrorResponse
                        .builder()
                        .code(e.getErrorCode().getCode())
                        .message(e.getErrorCode().getMessage())
                        .build()
                );

//        return ErrorResponse.builder(e, e.getErrorCode().getHttpStatus(), e.getMessage()).build();
    }

}
