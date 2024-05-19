package com.s207.cloudy.infra.cognito.dto;

import java.util.List;

public record JwksDto(
        List<JwkDto> keys
) {
}
