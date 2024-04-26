package com.s207.cloudy.domain.learning.dto.annotation;

import com.s207.cloudy.domain.learning.entity.enums.CourseType;
import com.s207.cloudy.global.error.exception.CustomValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TypeValidator implements ConstraintValidator<TypeValidation, String[]> {

    @Override
    public boolean isValid(String[] strings, ConstraintValidatorContext constraintValidatorContext) {

        if(strings != null) {
            for(int i=0; i<strings.length; i++) {
                try {
                    CourseType.getByCourse(strings[i]);
                } catch (CustomValidationException e) {
                    return false;
                }
            }
        }

        return true;
    }

}
