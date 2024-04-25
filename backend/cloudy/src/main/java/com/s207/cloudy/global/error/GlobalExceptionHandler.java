package com.s207.cloudy.global.error;

import com.s207.cloudy.domain.learning.exception.LearningException;
import com.s207.cloudy.global.error.enums.ErrorCodeEnum;
import com.s207.cloudy.global.error.exception.InvalidPaginationArgumentException;
import com.s207.cloudy.global.error.exception.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> validationExceptionHandler(CustomValidationException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getErrorMap(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LearningException.class)
    public ResponseEntity<?> learningExceptionHandler(LearningException e){
        log.error(e.getError().get("message"));
        return new ResponseEntity<>(e.getError(), e.getStatus());
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<ErrorResponse> serverErrorExceptionHandler(Exception e) {
//        return new ResponseEntity<ErrorResponse>(new ErrorResponse("SE001", "Internal Server Error입니다."), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler({InvalidPaginationArgumentException.class})
    public ResponseEntity<ErrorResponse> badRequestException400(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(ErrorCodeEnum.getEnum(e.getMessage())));
    }

}
