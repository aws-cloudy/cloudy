package com.s207.cloudy.domain.learning.dao;

import com.s207.cloudy.domain.learning.domain.Learning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@Repository
public interface LearningRepository extends JpaRepository<Learning, Integer>, LearningRepositoryCustom {

    @Query("SELECT l FROM Learning l JOIN LearningRoadmap lr ON l.id = lr.learning.id WHERE lr.roadmap.id = :roadmapId ORDER BY lr.rank")
    List<Learning> findByRoadmapId(@Param("roadmapId") int roadmapId);
}
