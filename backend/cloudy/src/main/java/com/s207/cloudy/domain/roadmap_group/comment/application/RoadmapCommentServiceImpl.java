package com.s207.cloudy.domain.roadmap_group.comment.application;

import com.s207.cloudy.domain.roadmap_group.comment.dao.RoadmapCommentRepository;
import com.s207.cloudy.domain.roadmap_group.comment.domain.RoadmapComment;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoadmapCommentServiceImpl implements RoadmapCommentService{
    private final RoadmapCommentRepository roadmapCommentRepository;

    @Override
    public List<RoadmapComment> getRoadmapEntityList(Roadmap roadmap) {
        return roadmapCommentRepository.findByRoadmap(roadmap);
    }
}
