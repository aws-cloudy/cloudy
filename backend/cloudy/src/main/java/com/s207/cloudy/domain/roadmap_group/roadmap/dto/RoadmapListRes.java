package com.s207.cloudy.domain.roadmap_group.roadmap.dto;

import java.util.List;

public record RoadmapListRes(
        List<RoadmapRes> roadmaps,
        long totalPageCnt) {

}
