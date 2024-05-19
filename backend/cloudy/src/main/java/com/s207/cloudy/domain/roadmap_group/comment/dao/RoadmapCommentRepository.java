package com.s207.cloudy.domain.roadmap_group.comment.dao;

import com.s207.cloudy.domain.roadmap_group.comment.domain.RoadmapComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoadmapCommentRepository extends JpaRepository<RoadmapComment, Integer> {

    @Query("SELECT rc FROM RoadmapComment rc JOIN FETCH rc.roadmap r WHERE r.id = :roadmapId")
    List<RoadmapComment> findByRoadmapId(@Param("roadmapId") int roadmapId);


    @Transactional
    @Modifying
    @Query("DELETE FROM RoadmapComment rc WHERE rc.id = :id")
    void deleteById(Integer id);

}


