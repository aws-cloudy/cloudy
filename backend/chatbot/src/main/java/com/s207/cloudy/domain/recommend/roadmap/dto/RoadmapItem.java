package com.s207.cloudy.domain.recommend.roadmap.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record RoadmapItem (
    int roadmapId,
    String title,
    String thumbnail,
    String service,
    String job,
    String summary
    ){
}
