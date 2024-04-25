package com.s207.cloudy.domain.members;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/members")
public class MembersController {

    @GetMapping("/roadmaps")
    public ResponseEntity<String> getMembersRoadmap(){
        return ResponseEntity.ok("security test");
    }

}
