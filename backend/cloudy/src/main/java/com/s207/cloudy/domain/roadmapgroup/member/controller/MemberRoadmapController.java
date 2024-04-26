package com.s207.cloudy.domain.roadmapGroup.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/roadmaps/my")
public class MemberRoadmapController {

    @GetMapping()
    public ResponseEntity<String> getMyRoadMaps(){


        return ResponseEntity.ok("ok");
    }


}
