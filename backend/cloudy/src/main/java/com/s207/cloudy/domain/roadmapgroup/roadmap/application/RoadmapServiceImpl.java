package com.s207.cloudy.domain.roadmapGroup.roadmap.application;

import com.s207.cloudy.domain.roadmapGroup.roadmap.dao.RoadmapQueryRepository;
import com.s207.cloudy.domain.roadmapGroup.roadmap.dto.RoadmapListRes;
import com.s207.cloudy.domain.roadmapGroup.roadmap.dto.RoadmapRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoadmapServiceImpl implements RoadmapService{

    private final RoadmapQueryRepository roadmapQueryRepository;

    @Override
    public RoadmapListRes getRoadmapList(String job, String service, String query, Pageable pageable) {
        Page<RoadmapRes> roadmaps = roadmapQueryRepository.findRoadmapList(job, service, query, pageable);
        return new RoadmapListRes(roadmaps.getContent(),roadmaps.getTotalPages());
    }
}
