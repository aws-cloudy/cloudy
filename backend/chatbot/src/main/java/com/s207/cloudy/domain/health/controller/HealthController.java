package com.s207.cloudy.domain.health.controller;


import com.s207.cloudy.domain.members.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class HealthController {



    @GetMapping
    public ResponseEntity<String> check(@AuthenticationPrincipal Member member){
        log.info("Member :: {}", member);
        return ResponseEntity.ok("ok");
    }


}
