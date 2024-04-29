package com.s207.cloudy.domain.roadmap_group.member.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/my")
@Slf4j
public class MemberRoadmapController {

    @GetMapping("/roadmaps")
    public ResponseEntity<String> getMyRoadMaps(){

        log.info("authentication :: {}", SecurityContextHolder.getContext().getAuthentication().getName());

        return ResponseEntity.ok("ok");
    }


}
