package com.s207.cloudy.domain.global.exception;

public class PaginationException extends RuntimeException{
    public PaginationException(String message) {
        super(message);
    }
}
