package com.s207.cloudy.global.error;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    // 유효성체크에 통과하지 못한 경우
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        ErrorResponse errorResponse = makeErrorResponse(e.getBindingResult());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse makeErrorResponse(BindingResult bindingResult){
        String code = "";
        String description = "";
        String detail = "";

        // 에러가 있다면
        if(bindingResult.hasErrors()){

            System.out.println(bindingResult.getFieldError());

            // DTO에 설정한 message값을 가져온다
            detail = bindingResult.getFieldError().getDefaultMessage();

            // DTO에 유효성체크를 걸어놓은 필드명을 가져온다.
            String bindResultCode = bindingResult.getFieldError().getField();

            switch (bindResultCode){
                case "pageSize":
                    code = ErrorCode.CE_002.getCode();
                    description = ErrorCode.CE_002.getDescription();
                    break;
                case "page":
                    code = ErrorCode.CE_003.getCode();
                    description = ErrorCode.CE_003.getDescription();
                    break;
            }

        }

        return new ErrorResponse(code, description, detail);
    }
}
