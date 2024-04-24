package com.s207.cloudy.domain.learning.dto.annotation;

import com.s207.cloudy.domain.learning.entity.enums.DifficultyType;
import com.s207.cloudy.global.handler.exception.CustomValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DifficultyValidator implements ConstraintValidator<DifficultyValidation, String[]> {

    @Override
    public boolean isValid(String[] strings, ConstraintValidatorContext constraintValidatorContext) {

        for(int i=0; i<strings.length; i++) {
            try {
                DifficultyType.getByCode(strings[i]);
            } catch (CustomValidationException e) {
                return false;
            }
        }

        return true;
    }

}
