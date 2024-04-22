package com.s207.cloudy.domain.roadmapgroup.roadmap.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RoadmapListRes {
    private int roadmapId;
    private String title;
    private String thumbnail;
    private String service;
    private String job;
    private String summary;
    private long commentsCnt;
}
