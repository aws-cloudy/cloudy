package com.s207.cloudy.domain.roadmap_group.comment.application;

import com.s207.cloudy.domain.roadmap_group.comment.dao.RoadmapCommentRepository;
import com.s207.cloudy.domain.roadmap_group.comment.domain.RoadmapComment;
import com.s207.cloudy.domain.roadmap_group.comment.dto.RoadmapCommentDto;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoadmapCommentServiceImpl implements RoadmapCommentService{
    private final RoadmapCommentRepository roadmapCommentRepository;

    @Override
    public List<RoadmapCommentDto> getRoadmapList(Integer roadmapId) {
        log.error("{}",roadmapCommentRepository.findByRoadmapId(roadmapId));
        return roadmapCommentRepository
                .findByRoadmapId(roadmapId)
                .stream()
                .map(RoadmapComment::toDto)
                .collect(Collectors.toList());
    }
}
