package com.s207.cloudy.domain.roadmap_group.comment.api;


import com.s207.cloudy.domain.roadmap_group.comment.application.RoadmapCommentService;
import com.s207.cloudy.domain.roadmap_group.comment.dto.RoadmapCommentPostReq;
import com.s207.cloudy.domain.roadmap_group.comment.dto.RoadmapCommentPostRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/comments")
public class CommentController {


    private final RoadmapCommentService roadmapCommentService;
    @PostMapping("/roadmaps")
    public ResponseEntity<RoadmapCommentPostRes> postRoadmapComment(@RequestBody RoadmapCommentPostReq roadmapCommentPostReq){
            return ResponseEntity
                    .ok( RoadmapCommentPostRes
                    .builder()
                    .id(roadmapCommentService.postRoadmapComment(roadmapCommentPostReq))
                    .build());
    }
}
