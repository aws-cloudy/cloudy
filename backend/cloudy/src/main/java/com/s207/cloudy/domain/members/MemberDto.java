package com.s207.cloudy.domain.members;

import lombok.Builder;

@Builder
public record MemberDto(
        String id,
        String name

) {

}
