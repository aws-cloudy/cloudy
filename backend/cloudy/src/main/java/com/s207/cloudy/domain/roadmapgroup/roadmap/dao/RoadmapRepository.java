package com.s207.cloudy.domain.roadmapgroup.roadmap.dao;

import com.s207.cloudy.domain.roadmapgroup.roadmap.domain.Roadmap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoadmapRepository extends JpaRepository<Roadmap, Integer> {
}
