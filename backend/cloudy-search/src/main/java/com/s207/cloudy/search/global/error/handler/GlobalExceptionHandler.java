package com.s207.cloudy.search.global.error.handler;

import com.s207.cloudy.search.global.error.dto.CommonErrorResponse;
import com.s207.cloudy.search.global.error.exception.OpensearchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({OpensearchException.class})
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

}
