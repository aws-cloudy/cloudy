package com.s207.cloudy.domain.learning.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class LearningSearchByJobReq {
    @Range(min=1, max=100, message = "1이상 100이하의 값만 가능합니다")
    private int count = 12;
}
