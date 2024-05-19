package com.s207.cloudy.domain.roadmap_group.comment.api;


import com.s207.cloudy.domain.members.domain.Member;
import com.s207.cloudy.domain.roadmap_group.comment.application.RoadmapCommentService;
import com.s207.cloudy.domain.roadmap_group.comment.dto.RoadmapCommentPostReq;
import com.s207.cloudy.domain.roadmap_group.comment.dto.RoadmapCommentPostRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/comments")
@Slf4j
public class CommentController {


    private final RoadmapCommentService roadmapCommentService;

    @PostMapping("/roadmaps")
    public ResponseEntity<RoadmapCommentPostRes> postRoadmapComment(@RequestBody RoadmapCommentPostReq roadmapCommentPostReq, @AuthenticationPrincipal Member member) {

        log.info("{}", member);

        return ResponseEntity
                .ok(
                        new RoadmapCommentPostRes(
                                roadmapCommentService.postRoadmapComment(roadmapCommentPostReq, member.getId())
                        )
                );
    }

    @DeleteMapping("/{commentId}/roadmaps")
    public ResponseEntity<RoadmapCommentPostRes> deleteRoadmapComment(
            @PathVariable("commentId") Integer commentId,

            @AuthenticationPrincipal Member member
    ) {


        return ResponseEntity.ok(
                new RoadmapCommentPostRes
                        (roadmapCommentService.deleteRoadmapComment(
                                commentId,
                                member.getId()
                        )
                        )
        );
    }
}
