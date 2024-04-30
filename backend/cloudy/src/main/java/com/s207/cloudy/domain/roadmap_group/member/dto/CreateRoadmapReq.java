package com.s207.cloudy.domain.roadmap_group.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateRoadmapReq {
    @NotNull(message = "로드맵 번호는 필수입니다.")
    private Integer roadmapId;
}
