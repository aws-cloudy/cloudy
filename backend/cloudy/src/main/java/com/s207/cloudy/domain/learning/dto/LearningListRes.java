package com.s207.cloudy.domain.learning.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
public class LearningListRes {
    private int learningId;
    private String thumbnail;
    private String serviceType;
    private String title;
    private String summary;
    private String duration;
    private String difficulty;
    private String link;
}
