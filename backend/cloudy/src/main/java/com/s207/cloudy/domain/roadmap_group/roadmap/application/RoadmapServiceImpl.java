package com.s207.cloudy.domain.roadmap_group.roadmap.application;

import com.s207.cloudy.domain.learning.domain.Learning;
import com.s207.cloudy.domain.learning.dao.LearningRepository;
import com.s207.cloudy.domain.roadmap_group.roadmap.dao.RoadmapQueryRepository;
import com.s207.cloudy.domain.roadmap_group.roadmap.dao.RoadmapRepository;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapDetailsRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapListRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.exception.RoadmapNotFoundException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoadmapServiceImpl implements RoadmapService {
    private final RoadmapRepository roadmapRepository;

    private final RoadmapQueryRepository roadmapQueryRepository;
    private final LearningRepository learningRepository;

    @Override
    public RoadmapListRes findRoadmapList(String job, String service, String query,
                                          Pageable pageable) {
        Page<RoadmapRes> roadmaps =
            roadmapQueryRepository.getRoadmaplist(job, service, query, pageable);
        return new RoadmapListRes(roadmaps.getContent(), roadmaps.getTotalPages());
    }

    @Override
    public Roadmap findRoadmapEntity(int roadmapId) {
        return roadmapRepository.findById(roadmapId)
            .orElseThrow(RoadmapNotFoundException::new);
    }

    @Override
    public RoadmapDetailsRes getRoadmapDetails(Integer roadmapId) {


        return RoadmapDetailsRes
            .builder()
            .detail(
                findRoadmapEntity(roadmapId).toDto()
            )
            .courses(
                learningRepository
                    .findByRoadmapId(roadmapId)
                    .stream()
                    .map(Learning::toDto)
                    .collect(Collectors.toList())
            )
            .build();


    }

}
