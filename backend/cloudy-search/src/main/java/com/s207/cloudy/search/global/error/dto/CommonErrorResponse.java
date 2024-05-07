package com.s207.cloudy.search.global.error.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class CommonErrorResponse {

    private String code;

    private String message;

    Map<String, Map<String, String>> errorMap;



}