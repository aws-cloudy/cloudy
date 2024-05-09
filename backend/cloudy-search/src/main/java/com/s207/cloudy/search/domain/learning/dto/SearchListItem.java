package com.s207.cloudy.search.domain.learning.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchListItem {

    private int learningId;

    private String title;

}
