package com.s207.cloudy.domain.roadmap_group.roadmap.application;

import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapDetailsRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapListRes;
import org.springframework.data.domain.Pageable;

public interface RoadmapService {
    RoadmapListRes findRoadmapList(String job, String service, String query, Pageable pageable);

    String replaceUnderBarToWhiteSpace(String param);

    Roadmap findRoadmapEntity(int roadmapId);

    RoadmapDetailsRes getRoadmapDetails(Integer roadmapId);
}
