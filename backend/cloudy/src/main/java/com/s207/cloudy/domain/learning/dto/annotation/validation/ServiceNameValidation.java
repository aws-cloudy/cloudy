package com.s207.cloudy.domain.learning.dto.annotation.validation;

import com.s207.cloudy.domain.learning.dto.annotation.validator.ServiceNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ServiceNameValidator.class)
public @interface ServiceNameValidation {
    String message() default "존재하지 않는 서비스명입니다";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
