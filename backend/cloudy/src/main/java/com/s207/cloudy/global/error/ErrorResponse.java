package com.s207.cloudy.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class ErrorResponse<T> {
    private final T errors;
}