package com.s207.cloudy.domain.members.dto;

import lombok.Builder;

@Builder
public record MemberDto(
        String id,
        String name

) {

}
