package com.s207.cloudy.search.domain.learning.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchListRes {

    private List<SearchListItem> searchList;

}
