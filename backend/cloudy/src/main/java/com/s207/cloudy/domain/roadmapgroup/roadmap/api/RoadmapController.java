package com.s207.cloudy.domain.roadmapGroup.roadmap.api;

import com.s207.cloudy.domain.roadmapGroup.roadmap.application.RoadmapService;
import com.s207.cloudy.domain.roadmapGroup.roadmap.dto.RoadmapListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/roadmaps")
public class RoadmapController {
    private final RoadmapService roadmapService;

    @GetMapping
    public ResponseEntity<RoadmapListRes> getRoadmapList(@RequestParam(required = false) String job,
                                                         @RequestParam(required = false) String service,
                                                         @RequestParam(required = false) String query,
                                                         @PageableDefault Pageable pageable) {

        return ResponseEntity
                .status(OK)
                .body(roadmapService.getRoadmapList(job, service, query, pageable));
    }
}
