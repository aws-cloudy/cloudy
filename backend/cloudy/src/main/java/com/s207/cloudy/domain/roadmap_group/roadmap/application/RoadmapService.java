package com.s207.cloudy.domain.roadmap_group.roadmap.application;

import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapListRes;
import org.springframework.data.domain.Pageable;

public interface RoadmapService {
    RoadmapListRes getRoadmapList(String job, String service, String query, Pageable pageable);
}
