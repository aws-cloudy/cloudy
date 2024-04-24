package com.s207.cloudy.global.handler;

import com.s207.cloudy.domain.learning.exception.LearningErrorCode;
import com.s207.cloudy.domain.learning.exception.LearningException;
import com.s207.cloudy.global.error.ErrorResponse;
import com.s207.cloudy.global.handler.exception.CustomValidationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> validationApiException(CustomValidationException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getErrorMap(), HttpStatus.BAD_REQUEST);
    }
//    @ExceptionHandler(LearningException.class)
//    public ResponseEntity<ErrorResponse> learningExceptionHandler(LearningException e){
//        return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getErrorCode().getCode(), e.getErrorCode().getMessage()), e.getErrorCode().getHttpStatus());
//    }
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<ErrorResponse> serverErrorExceptionHandler(Exception e) {
//        return new ResponseEntity<ErrorResponse>(new ErrorResponse("SE001", "Internal Server Error입니다."), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, ErrorResponse>> methodValidExceptionHandler(MethodArgumentNotValidException e){
//        BindingResult bindingResult = e.getBindingResult();
//        Map<String, ErrorResponse> errorMaps = new HashMap<>();
//
//        // 에러가 있다면
//        if(bindingResult.hasErrors()) {
//            Map<String, String> errorMap = new HashMap<>();
//
//            for(FieldError error : bindingResult.getFieldErrors()) {
//                errorMap.put(error.getField(), error.getDefaultMessage());
//            }
//
//            for (String error : errorMap.keySet()) {
//                errorResponseList.put(error, new ErrorResponse("CE001", errorMap.get(error)));
//            }
//        }
//        return new ResponseEntity<Map<String, ErrorResponse>>(errorResponseList, HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler(CustomValidationException.class)
//    public ResponseEntity<List<ErrorResponse>> customValidExceptionHandler(CustomValidationException e){
////        return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getErrorCode().getCode(), e.getErrorCode().getMessage()), e.getErrorCode().getHttpStatus());
//        System.out.println("--");
//        List<ErrorResponse> errorResponseList = new ArrayList<>();
//        System.out.println("==" + e.getErrorMap().keySet());
//        for (String error : e.getErrorMap().keySet()) {
//            errorResponseList.add(new ErrorResponse("CE001", e.getErrorMap().get(error)));
//        }
//
//        return new ResponseEntity<List<ErrorResponse>>(errorResponseList, HttpStatus.BAD_REQUEST);
//    }

}
