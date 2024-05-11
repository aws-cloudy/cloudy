package com.s207.cloudy.domain.roadmap_group.comment.application;

import com.s207.cloudy.domain.roadmap_group.comment.dto.RoadmapCommentDto;
import com.s207.cloudy.domain.roadmap_group.comment.dto.RoadmapCommentPostReq;

import java.util.List;

public interface RoadmapCommentService {
    List<RoadmapCommentDto> getRoadmapCommentList(Integer roadmapId);

    Integer postRoadmapComment(RoadmapCommentPostReq roadmapCommentPostReq, String userId);
}
