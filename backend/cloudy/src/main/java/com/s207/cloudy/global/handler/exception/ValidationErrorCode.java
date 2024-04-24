package com.s207.cloudy.global.handler.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum ValidationErrorCode {
    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_DIFFICULTY("CE001", "difficulty", HttpStatus.BAD_REQUEST),
    INVALID_TYPE("CE001", "type", HttpStatus.BAD_REQUEST),
    INVALID_JOB_NAME("CE001", "jobName", HttpStatus.BAD_REQUEST),
    INVALID_SERVICE_NAME("CE001", "serviceName", HttpStatus.BAD_REQUEST),
    INVALID_PAGE_SIZE("CE002", "pageSize", HttpStatus.BAD_REQUEST),
    INVALID_PAGE("CE003", "page", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String field;
    private final HttpStatus httpStatus;

    public static String getByField(String field) {
        for (ValidationErrorCode errorCode : values()) {
            if (errorCode.getField().equals(field)) {
                return errorCode.getCode();
            }
        }
        return null;
    }
}


