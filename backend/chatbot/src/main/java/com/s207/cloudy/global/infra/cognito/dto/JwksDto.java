package com.s207.cloudy.global.infra.cognito.dto;

import java.util.List;

public record JwksDto(
        List<JwkDto> keys
) {
}
