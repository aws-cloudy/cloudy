package com.s207.cloudy.domain.roadmap_group.comment.dto;

import lombok.ToString;

import java.util.List;

public record RoadmapCommentListRes(
        List<RoadmapCommentDto> comments
) {
}
