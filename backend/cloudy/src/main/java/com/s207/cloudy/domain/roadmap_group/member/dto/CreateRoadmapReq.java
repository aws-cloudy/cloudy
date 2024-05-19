package com.s207.cloudy.domain.roadmap_group.member.dto;

import jakarta.validation.constraints.NotNull;

public record CreateRoadmapReq(
        @NotNull(message = "로드맵 번호는 필수입니다.")
        Integer roadmapId
) {

}
