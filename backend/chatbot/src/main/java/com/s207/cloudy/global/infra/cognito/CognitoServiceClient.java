package com.s207.cloudy.global.infra.cognito;


import com.s207.cloudy.global.infra.cognito.dto.JwksDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="cognito-service", url = "${cognito.iss}")
public interface CognitoServiceClient {

    @GetMapping("/jwks.json")
    ResponseEntity<JwksDto> getJwks();


}
