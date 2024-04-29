package com.s207.cloudy.global.config.aop;

import com.s207.cloudy.global.error.exception.CustomValidationException;
import com.s207.cloudy.global.error.enums.ValidationErrorCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class CustomValidationAdvice {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping() {
    }


    @Around("getMapping()") // joinPoint의 전후 제어
    public Object validationAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs(); // joinPoint의 매개변수
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;

                if (bindingResult.hasErrors()) {
                    Map<String, Map<String, String>> errorMap = new HashMap<>();

                    for (FieldError error : bindingResult.getFieldErrors()) {
                        String field = error.getField();
                        Map<String, String> codeAndMessage = new HashMap<>();
                        codeAndMessage.put("code", ValidationErrorCode.getByField(field));
                        codeAndMessage.put("message", error.getDefaultMessage());
                        errorMap.put(field, codeAndMessage);
                    }
                    throw new CustomValidationException("유효성검사 실패", errorMap);
                }
            }
        }
        return proceedingJoinPoint.proceed(); // 정상적으로 해당 메서드를 실행
    }
}