package com.s207.cloudy.domain.roadmap_group.comment.application;

import com.s207.cloudy.domain.roadmap_group.comment.domain.RoadmapComment;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;

import java.util.List;

public interface RoadmapCommentService {
    List<RoadmapComment> getRoadmapEntityList(Roadmap roadmap);
}
