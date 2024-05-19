package com.s207.cloudy.domain.roadmap_group.roadmap.dto;


import com.s207.cloudy.domain.learning.dto.LearningItem;
import lombok.Builder;

import java.util.List;

@Builder
public record RoadmapDetailsRes(

        RoadmapRes detail,
        List<LearningItem> courses


) {
}
