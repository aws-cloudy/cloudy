package com.s207.cloudy.domain.roadmapGroup.roadmap.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoadmapListRes {
    private List<RoadmapRes> roadmaps;
    private long totalPageCnt;
}
