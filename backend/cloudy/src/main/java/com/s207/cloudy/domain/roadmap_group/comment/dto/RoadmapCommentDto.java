package com.s207.cloudy.domain.roadmap_group.comment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.s207.cloudy.domain.members.dto.MemberDto;
import lombok.Builder;

import java.time.LocalDateTime;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record RoadmapCommentDto(
    int commentId,
    Integer roadmapId,
    MemberDto member,
    String content,
    LocalDateTime regAt
) {
}
