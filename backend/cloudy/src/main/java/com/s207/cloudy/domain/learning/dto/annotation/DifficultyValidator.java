package com.s207.cloudy.domain.learning.dto.annotation;

import com.s207.cloudy.domain.learning.entity.enums.DifficultyType;
import com.s207.cloudy.global.error.exception.CustomValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class DifficultyValidator implements ConstraintValidator<DifficultyValidation, String[]> {

    @Override
    public boolean isValid(String[] strings, ConstraintValidatorContext constraintValidatorContext) {
        if(strings==null){
            return true;
        }
        //변경 전 코드
//        for(int i=0; i<strings.length; i++) {
//            try {
//                DifficultyType.getByCode(strings[i]);
//            } catch (CustomValidationException e) {
//                return false;
//            }
//        }

        //의문 굳이 예외를 false로 반환해야하는가??
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
