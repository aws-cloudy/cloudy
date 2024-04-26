package com.s207.cloudy.domain.roadmapGroup.comment.application;

import com.s207.cloudy.domain.roadmapGroup.comment.dao.RoadmapCommentRepository;
import com.s207.cloudy.domain.roadmapGroup.comment.domain.RoadmapComment;
import com.s207.cloudy.domain.roadmapGroup.roadmap.domain.Roadmap;
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
