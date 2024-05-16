package com.s207.cloudy.domain.roadmap_group.roadmap.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record RoadmapRes(int roadmapId,
                         String title,
                         String thumbnail,
                         String service,
                         String job,
                         String summary,
                         String desc,
                         long commentsCnt) {

}
