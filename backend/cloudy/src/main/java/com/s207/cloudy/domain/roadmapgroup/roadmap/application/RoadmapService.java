package com.s207.cloudy.domain.roadmapgroup.roadmap.application;

import com.s207.cloudy.domain.roadmapgroup.roadmap.dto.RoadmapListRes;
import org.springframework.data.domain.Pageable;

public interface RoadmapService {
    RoadmapListRes getRoadmapList(String job, String service, String query, Pageable pageable);
}
