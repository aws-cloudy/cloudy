package com.s207.cloudy.search.global.error.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum ErrorCode {

    //Opensearch 에러
    OPENSEARCH_CONNECTION_ERROR("OE001", "Opensearch 연결에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    //Redis 에러
    JSON_PARSING_ERROR("RE001", "캐시에 데이터를 저장하거나 읽어들이는데 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    //서버에러
    SERVER_ERROR("SE001", "Internal Server Error / 데이터베이스 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    //요청에러
    PARAMETER_ERROR("CE001", "API 요청 URL의 프로토콜, 파라미터 등에 오류가 있습니다.", HttpStatus.BAD_REQUEST);

    private final String code;

    private final String message;

    private final HttpStatus httpStatus;


    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public static ErrorCode getEnum(String message) {
        return Arrays.stream(values())
                .filter(val -> Objects.equals(message, val.message))
                .findFirst()
                .orElse(ErrorCode.SERVER_ERROR);

    }
}
