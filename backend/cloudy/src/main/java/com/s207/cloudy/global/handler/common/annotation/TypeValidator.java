package com.s207.cloudy.global.handler.common.annotation;

import com.s207.cloudy.domain.learning.entity.enums.CourseType;
import com.s207.cloudy.domain.learning.entity.enums.DifficultyType;
import com.s207.cloudy.domain.learning.exception.LearningException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TypeValidator implements ConstraintValidator<TypeValidation, String[]> {

    @Override
    public boolean isValid(String[] strings, ConstraintValidatorContext constraintValidatorContext) {

        for(int i=0; i<strings.length; i++) {
            try {
                System.out.println(strings[i]);
                CourseType.getByCourse(strings[i]);
            } catch (LearningException e) {
                return false;
            }
        }

        return true;
    }

}
