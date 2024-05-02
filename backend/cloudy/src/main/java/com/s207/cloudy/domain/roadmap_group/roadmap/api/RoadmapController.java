package com.s207.cloudy.domain.roadmap_group.roadmap.api;

import com.s207.cloudy.domain.roadmap_group.comment.application.RoadmapCommentService;
import com.s207.cloudy.domain.roadmap_group.comment.dto.RoadmapCommentListRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.application.RoadmapService;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapDetailsRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapListRes;
import jakarta.ws.rs.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/roadmaps")
public class RoadmapController {
    private final RoadmapService roadmapService;
    private final RoadmapCommentService roadmapCommentService;

    @GetMapping
    public ResponseEntity<RoadmapListRes> getRoadmapList(@RequestParam(required = false) String job,
                                                         @RequestParam(required = false) String service,
                                                         @RequestParam(required = false) String query,
                                                         @PageableDefault Pageable pageable) {

        return ResponseEntity
                .status(OK)
                .body(roadmapService.findRoadmapList(job, service, query, pageable));
    }


    @GetMapping("/{roadmapId}")
    public ResponseEntity<RoadmapDetailsRes> getRoadMapDetails(@PathVariable("roadmapId")Integer roadmapId){
        return ResponseEntity.ok(
                roadmapService.getRoadmapDetails(roadmapId)
        );

    }

    @GetMapping("/{roadmapId}/comments")
    public ResponseEntity<RoadmapCommentListRes> getRoadmapCommentList(@PathVariable("roadmapId")Integer roadmapId){
        return ResponseEntity.ok(
                new RoadmapCommentListRes(roadmapCommentService.getRoadmapList(roadmapId))
        );
    }

}
