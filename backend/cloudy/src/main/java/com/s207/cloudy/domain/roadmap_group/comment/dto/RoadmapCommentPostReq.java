package com.s207.cloudy.domain.roadmap_group.comment.dto;

public record RoadmapCommentPostReq(
        Integer roadmapId,
        String memberId,
        String content

) {
}
