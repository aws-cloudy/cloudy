package com.s207.cloudy.domain.learning.dto;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
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
    @Min(value = 1, message = "page 파라미터 값은 양수입니다.")
    private int page = 1;

    @Range(min=1, max=100, message = "pageSize 파라미터 값의 허용 범위는 1이상 100이하 입니다.")
    private int pageSize = 10;

    private String[] jobName;

    String[] serviceName;

    String[] type;

    String[] difficulty;

    String query;
}
