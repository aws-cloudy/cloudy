package com.s207.cloudy.domain.learning.dto.annotation.validator;

import com.s207.cloudy.domain.learning.dto.annotation.validation.ServiceNameValidation;
import com.s207.cloudy.domain.learning.domain.enums.ServiceNameType;
import com.s207.cloudy.global.error.exception.CustomValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class ServiceNameValidator implements ConstraintValidator<ServiceNameValidation, String[]> {

    @Override
    public boolean isValid(String[] strings, ConstraintValidatorContext constraintValidatorContext) {
        if(strings == null){
            return true;
        }

       return validateServiceNameTypeArray(strings);
    }



    private boolean validateServiceNameTypeArray(String[] strings){
        return Arrays.stream(strings)
                .allMatch(code -> {
                    try {
                        ServiceNameType.getByServiceName(code);
                        return true;
                    } catch (CustomValidationException e) {
                        return false;
                    }
                });
    }

}
