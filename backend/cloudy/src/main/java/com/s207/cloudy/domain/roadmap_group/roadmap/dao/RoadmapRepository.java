package com.s207.cloudy.domain.roadmap_group.roadmap.dao;

import com.s207.cloudy.domain.learning.entity.Learning;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoadmapRepository extends JpaRepository<Roadmap, Integer> {

    Optional<Roadmap> getRoadmapsById(Integer id);



}
