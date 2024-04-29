package com.s207.cloudy.domain.roadmap_group.roadmap.application;

import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapListRes;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoadmapService {
    RoadmapListRes findRoadmapList(String job, String service, String query, Pageable pageable);

    RoadmapListRes findMemberRoadmapList(List<Integer> memberRoadmapList);

    Roadmap findRoadmapEntity(int roadmapId);
}
