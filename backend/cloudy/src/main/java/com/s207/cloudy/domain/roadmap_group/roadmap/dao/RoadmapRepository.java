package com.s207.cloudy.domain.roadmap_group.roadmap.dao;

import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoadmapRepository extends JpaRepository<Roadmap, Integer> {
}
