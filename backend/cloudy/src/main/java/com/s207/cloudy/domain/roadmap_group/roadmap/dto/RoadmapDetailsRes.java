package com.s207.cloudy.domain.roadmap_group.roadmap.dto;


import com.s207.cloudy.domain.learning.dto.LearningItem;
import java.util.List;
import lombok.Builder;


@Builder
public record RoadmapDetailsRes(

        RoadmapRes detail,
        List<LearningItem> courses


) {
}
