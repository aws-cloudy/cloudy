package com.s207.cloudy.domain.roadmap_group.comment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.s207.cloudy.domain.members.MemberDto;
import com.s207.cloudy.domain.members.entity.Member;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RoadmapCommentDto(


        int commentId,
        Roadmap roadmap,
        MemberDto member,
        String content,
        LocalDateTime regAt
) {
}
