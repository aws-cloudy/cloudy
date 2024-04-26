package com.s207.cloudy.domain.roadmapGroup.comment.application;

import com.s207.cloudy.domain.roadmapGroup.comment.domain.RoadmapComment;
import com.s207.cloudy.domain.roadmapGroup.roadmap.domain.Roadmap;

import java.util.List;

public interface RoadmapCommentService {
    List<RoadmapComment> getRoadmapEntityList(Roadmap roadmap);
}
