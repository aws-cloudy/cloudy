package com.s207.cloudy.domain.learning.dto.annotation.validator;

import com.s207.cloudy.domain.learning.dto.annotation.validation.DifficultyValidation;
import com.s207.cloudy.domain.learning.entity.enums.DifficultyType;
import com.s207.cloudy.global.error.exception.CustomValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class DifficultyValidator implements ConstraintValidator<DifficultyValidation, String[]> {

    @Override
    public boolean isValid(String[] strings, ConstraintValidatorContext constraintValidatorContext) {
        if(strings == null){
            return true;
        }

        return validateDifficultTypeArray(strings);
    }


    private boolean validateDifficultTypeArray(String[] strings){
        return Arrays.stream(strings)
                .allMatch(code -> {
                    try {
                        DifficultyType.getByCode(code);
                        return true;
                    } catch (CustomValidationException e) {
                        return false;
                    }
                });
    }

}
