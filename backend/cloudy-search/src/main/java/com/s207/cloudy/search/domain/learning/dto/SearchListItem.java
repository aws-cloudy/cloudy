package com.s207.cloudy.search.domain.learning.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchListItem {

    private String learningId;

    private String title;

    private String documentId;

}
