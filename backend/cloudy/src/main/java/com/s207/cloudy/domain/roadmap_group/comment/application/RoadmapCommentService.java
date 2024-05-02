package com.s207.cloudy.domain.roadmap_group.comment.application;

import com.s207.cloudy.domain.roadmap_group.comment.domain.RoadmapComment;
import com.s207.cloudy.domain.roadmap_group.comment.dto.RoadmapCommentDto;
import com.s207.cloudy.domain.roadmap_group.comment.dto.RoadmapCommentPostReq;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;

import java.util.List;

public interface RoadmapCommentService {
    List<RoadmapCommentDto> getRoadmapList(Integer roadmapId);

    Integer postRoadmapComment(RoadmapCommentPostReq roadmapCommentPostReq);
}
