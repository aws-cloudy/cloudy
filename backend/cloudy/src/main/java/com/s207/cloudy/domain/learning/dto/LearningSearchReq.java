package com.s207.cloudy.domain.learning.dto;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import com.s207.cloudy.global.handler.common.annotation.TypeValidation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.RequestParam;

@Setter
@Getter
@ToString
public class LearningSearchReq {
    @Min(value = 1, message = "1이상의 값만 가능합니다")
    private int page = 1;

    @Range(min=1, max=100, message = "1이상 100이하의 값만 가능합니다")
    private int pageSize = 10;

    private String[] jobName;

    String[] serviceName;

    @TypeValidation
    String[] type;

    String[] difficulty;

    String query;
}
