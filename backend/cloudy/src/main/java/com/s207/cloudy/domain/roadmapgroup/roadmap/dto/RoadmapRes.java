package com.s207.cloudy.domain.roadmapgroup.roadmap.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoadmapRes {
    private int roadmapId;
    private String title;
    private String thumbnail;
    private String service;
    private String job;
    private String summary;
    private long commentsCnt;
}
