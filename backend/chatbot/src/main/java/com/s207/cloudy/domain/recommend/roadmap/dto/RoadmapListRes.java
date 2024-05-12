package com.s207.cloudy.domain.recommend.roadmap.dto;




import lombok.Builder;

import java.util.List;


@Builder
public record RoadmapListRes(
        List<RoadmapItem> roadmaps
) {
}
