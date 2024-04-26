package com.s207.cloudy.domain.roadmapGroup.roadmap.dao;

import com.s207.cloudy.domain.roadmapGroup.roadmap.domain.Roadmap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoadmapRepository extends JpaRepository<Roadmap, Integer> {
}
