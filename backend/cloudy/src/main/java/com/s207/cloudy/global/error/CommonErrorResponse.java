package com.s207.cloudy.global.error;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.s207.cloudy.global.error.enums.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;

import java.util.Map;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class CommonErrorResponse {

    private String code;

    private String message;

    Map<String, Map<String, String>> errorMap;



}