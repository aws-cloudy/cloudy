package com.s207.cloudy.infra.cognito.api;


import com.s207.cloudy.infra.cognito.dto.JwksDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="cognito-service", url = "${cognito.iss}")
public interface CognitoServiceClient {

    @GetMapping("/jwks.json")
    ResponseEntity<JwksDto> getJwks();


}
