package com.s207.cloudy.search.global.error.handler;

import com.s207.cloudy.search.global.error.dto.CommonErrorResponse;
import com.s207.cloudy.search.global.error.enums.ErrorCode;
import com.s207.cloudy.search.global.error.exception.RedisException;
import com.s207.cloudy.search.global.error.exception.OpensearchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OpensearchException.class)
    public ResponseEntity<CommonErrorResponse> badRequestException500(OpensearchException e) {
        log.error("Exception type : {}, message :{}", e.getClass(), e.getMessage());
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(CommonErrorResponse
                        .builder()
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(RedisException.class)
    public ResponseEntity<CommonErrorResponse> badRequestException500(RedisException e) {
        log.error("Exception type : {}, message :{}", e.getClass(), e.getMessage());
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(CommonErrorResponse
                        .builder()
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build()
                );
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<CommonErrorResponse> badRequestException400(RuntimeException e) {
        log.error("Exception type : {}, message :{}", e.getClass(), e.getMessage());
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(CommonErrorResponse
                        .builder()
                        .code(ErrorCode.PARAMETER_ERROR.getCode())
                        .message(ErrorCode.PARAMETER_ERROR.getMessage())
                        .build()
                );
    }


}
