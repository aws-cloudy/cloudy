package com.s207.cloudy.domain.recommend.roadmap.dao;

import com.s207.cloudy.domain.recommend.roadmap.domain.Roadmap;
import com.s207.cloudy.domain.recommend.roadmap.dto.RoadmapItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoadmapRepository extends JpaRepository<Roadmap, Integer> {
    @Query("SELECT new com.s207.cloudy.domain.recommend.roadmap.dto.RoadmapItem(r.id, r.title, r.thumbnail, r.service, r.job, r.summary)FROM Roadmap as r " +
            "WHERE r.id IN :ids")
    List<RoadmapItem> findAllByIds(@Param("ids") List<Integer> ids);

}
