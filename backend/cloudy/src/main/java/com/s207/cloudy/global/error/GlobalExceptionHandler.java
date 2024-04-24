package com.s207.cloudy.global.error;

import com.s207.cloudy.domain.learning.exception.LearningErrorCode;
import com.s207.cloudy.domain.learning.exception.LearningException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LearningException.class)
    public ResponseEntity<ErrorResponse> learningExceptionHandler(LearningException e){
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getErrorCode().getCode(), e.getErrorCode().getMessage()), e.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> serverErrorExceptionHandler(Exception e) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse("SE001", "Internal Server Error입니다."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        ErrorResponse errorResponse = makeErrorResponse(e.getBindingResult());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse makeErrorResponse(BindingResult bindingResult){
        // 에러가 있다면
        if(bindingResult.hasErrors()){
            // DTO에 유효성체크를 걸어놓은 필드명을 가져온다
            String bindResultCode = bindingResult.getFieldError().getField();

            switch (bindResultCode){
                case "pageSize":
                    LearningErrorCode ec = LearningErrorCode.INVALID_PAGE_SIZE;
                    return new ErrorResponse(ec.getCode(), ec.getMessage());
                case "page":
                    ec = LearningErrorCode.INVALID_PAGE;
                    return new ErrorResponse(ec.getCode(), ec.getMessage());
            }
        }
        return null;
    }
}
