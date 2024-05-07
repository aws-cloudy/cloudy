package com.s207.cloudy.domain.learning.dto.annotation.validator;

import com.s207.cloudy.domain.learning.dto.annotation.validation.JobNameValidation;
import com.s207.cloudy.domain.learning.entity.enums.JobNameType;
import com.s207.cloudy.global.error.exception.CustomValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class JobNameValidator implements ConstraintValidator<JobNameValidation, String[]> {

    @Override
    public boolean isValid(String[] strings, ConstraintValidatorContext constraintValidatorContext) {
        if(strings == null){
            return true;
        }

       return validateJobNameTypeArray(strings);
    }



    private boolean validateJobNameTypeArray(String[] strings){
        return Arrays.stream(strings)
                .allMatch(code -> {
                    try {
                        JobNameType.getByJobName(code);
                        return true;
                    } catch (CustomValidationException e) {
                        return false;
                    }
                });
    }

}
