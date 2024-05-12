package com.s207.cloudy.domain.roadmap_group.roadmap.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class RoadmapRes {
    private int roadmapId;
    private String title;
    private String thumbnail;
    private String service;
    private String job;
    private String summary;
    private String desc;
    private long commentsCnt;
}
