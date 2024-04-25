package com.s207.cloudy.domain.roadmapgroup.comment.application;

import com.s207.cloudy.domain.roadmapgroup.comment.dao.RoadmapCommentRepository;
import com.s207.cloudy.domain.roadmapgroup.comment.domain.RoadmapComment;
import com.s207.cloudy.domain.roadmapgroup.roadmap.domain.Roadmap;
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
