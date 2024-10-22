package com.s207.cloudy.domain.learning.dto.annotation.validator;

import com.s207.cloudy.domain.learning.dto.annotation.validation.TypeValidation;
import com.s207.cloudy.domain.learning.domain.enums.CourseType;
import com.s207.cloudy.global.error.exception.CustomValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class TypeValidator implements ConstraintValidator<TypeValidation, String[]> {

    @Override
    public boolean isValid(String[] strings, ConstraintValidatorContext constraintValidatorContext) {
        if(strings == null){
            return true;
        }

       return validateCourseTypeArray(strings);
    }



    private boolean validateCourseTypeArray(String[] strings){
        return Arrays.stream(strings)
                .allMatch(code -> {
                    try {
                        CourseType.getByCourse(code);
                        return true;
                    } catch (CustomValidationException e) {
                        return false;
                    }
                });
    }

}
