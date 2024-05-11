package com.s207.cloudy.domain.roadmap_group.comment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.s207.cloudy.domain.members.MemberDto;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RoadmapCommentDto(
    int commentId,
    Integer roadmapId,
    MemberDto member,
    String content,
    LocalDateTime regAt
) {
}
