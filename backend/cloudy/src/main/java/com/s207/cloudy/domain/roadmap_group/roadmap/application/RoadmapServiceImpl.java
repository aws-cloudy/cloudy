package com.s207.cloudy.domain.roadmap_group.roadmap.application;

import com.s207.cloudy.domain.roadmap_group.roadmap.dao.RoadmapQueryRepository;
import com.s207.cloudy.domain.roadmap_group.roadmap.dao.RoadmapRepository;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapListRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.exception.RoadmapNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoadmapServiceImpl implements RoadmapService {
    private final RoadmapRepository roadmapRepository;

    private final RoadmapQueryRepository roadmapQueryRepository;

    @Override
    public RoadmapListRes findRoadmapList(String job, String service, String query, Pageable pageable) {
        Page<RoadmapRes> roadmaps = roadmapQueryRepository.getRoadmaplist(job, service, query, pageable);
        return new RoadmapListRes(roadmaps.getContent(), roadmaps.getTotalPages());
    }

    @Override
    public Roadmap findRoadmapEntity(int roadmapId) {
        return roadmapRepository.findById(roadmapId)
                .orElseThrow(RoadmapNotFoundException::new);
    }

    @Override
    public RoadmapListRes findMemberRoadmapList(List<Integer> memberRoadmapIdList) {
        Page<RoadmapRes> roadmaps = roadmapQueryRepository.getMemberRoadmapList(memberRoadmapIdList);
        return new RoadmapListRes(roadmaps.getContent(), roadmaps.getTotalPages());
    }
}
