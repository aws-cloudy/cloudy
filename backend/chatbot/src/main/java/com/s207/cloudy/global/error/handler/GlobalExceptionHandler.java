package com.s207.cloudy.global.error.handler;


import com.s207.cloudy.global.auth.error.exception.AuthorizationException;
import com.s207.cloudy.global.error.dto.CommonErrorResponse;
import com.s207.cloudy.global.error.enums.ErrorCode;
import com.s207.cloudy.global.error.exception.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.s207.cloudy.global.infra.modertation.ModerationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.s207.cloudy.global.error.enums.ErrorCode.PARAMETER_ERROR;


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

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<CommonErrorResponse> missingServletRequestParameterException(MissingServletRequestParameterException e){
        log.error(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        CommonErrorResponse
                                .builder()
                                .code(PARAMETER_ERROR.getCode())
                                .message(PARAMETER_ERROR.getMessage())
                                .build()
                );


    }

    @ExceptionHandler(ModerationException.class)
    public ResponseEntity<CommonErrorResponse> moderationExceptionHandler(ModerationException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(
                        CommonErrorResponse
                                .builder()
                                .code(e.getCode())
                                .message(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<CommonErrorResponse> illegalStateExceptionHandler(IllegalArgumentException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        CommonErrorResponse
                                .builder()
                                .code(PARAMETER_ERROR.getCode())
                                .message(PARAMETER_ERROR.getMessage())
                                .build()
                );

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CommonErrorResponse> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        CommonErrorResponse
                                .builder()
                                .code(PARAMETER_ERROR.getCode())
                                .message(PARAMETER_ERROR.getMessage())
                                .build()
                );

    }


    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<CommonErrorResponse> commonExceptionHandler(AuthorizationException e) {
        log.error("Exception type : {}, message :{}", e.getClass(), e.getErrorCode().getMessage());


        return ResponseEntity
                .status(e.getHttpStatus())
                .body(
                        CommonErrorResponse
                                .builder()
                                .code(e.getCode())
                                .message(e.getMessage())
                                .build()
                );

    }

}
