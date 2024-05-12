package com.s207.cloudy.global.error.handler;

import com.s207.cloudy.domain.learning.exception.LearningException;
import com.s207.cloudy.domain.roadmap_group.member.exception.MemberRoadmapException;
import com.s207.cloudy.domain.roadmap_group.roadmap.exception.RoadmapNotFoundException;
import com.s207.cloudy.global.auth.error.exception.AuthorizationException;
import com.s207.cloudy.global.error.dto.CommonErrorResponse;
import com.s207.cloudy.global.error.enums.ErrorCode;
import com.s207.cloudy.global.error.exception.CustomValidationException;
import com.s207.cloudy.global.error.exception.InvalidPaginationArgumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;


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

    @ExceptionHandler(LearningException.class)
    public ResponseEntity<CommonErrorResponse> learningExceptionHandler(LearningException e) {
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


    @ExceptionHandler({InvalidPaginationArgumentException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<CommonErrorResponse> badRequestException400(InvalidPaginationArgumentException e) {
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

    @ExceptionHandler({RoadmapNotFoundException.class, MemberRoadmapException.class})
    public ResponseEntity<CommonErrorResponse> memberExceptionHandler(RuntimeException e) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(CommonErrorResponse
                        .builder()
                        .code(ErrorCode.PARAMETER_ERROR.getCode())
                        .message(e.getMessage())
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
