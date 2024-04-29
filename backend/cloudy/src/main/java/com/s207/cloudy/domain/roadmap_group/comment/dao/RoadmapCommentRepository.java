package com.s207.cloudy.domain.roadmap_group.comment.dao;

import com.s207.cloudy.domain.roadmap_group.comment.domain.RoadmapComment;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadmapCommentRepository extends JpaRepository<RoadmapComment, Integer> {
    List<RoadmapComment> findByRoadmap(Roadmap roadmap);
}
