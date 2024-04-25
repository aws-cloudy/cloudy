package com.s207.cloudy.domain.roadmapgroup.comment.application;

import com.s207.cloudy.domain.roadmapgroup.comment.domain.RoadmapComment;
import com.s207.cloudy.domain.roadmapgroup.roadmap.domain.Roadmap;

import java.util.List;

public interface RoadmapCommentService {
    List<RoadmapComment> getRoadmapEntityList(Roadmap roadmap);
}
