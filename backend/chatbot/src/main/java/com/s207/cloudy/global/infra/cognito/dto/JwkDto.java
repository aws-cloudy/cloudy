package com.s207.cloudy.global.infra.cognito.dto;

public record JwkDto(
        String alg,
        String e,
        String kid,
        String kty,
        String n,
        String use
) {


    public boolean equalsKid(String kid){
        return this.kid.equals(kid);
    };
}
