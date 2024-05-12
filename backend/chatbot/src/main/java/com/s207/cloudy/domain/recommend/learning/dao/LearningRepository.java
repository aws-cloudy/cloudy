package com.s207.cloudy.domain.recommend.learning.dao;

import com.s207.cloudy.domain.recommend.learning.domain.Learning;
import com.s207.cloudy.domain.recommend.learning.dto.LearningItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningRepository extends JpaRepository<Learning, Integer>{
    @Query("SELECT new com.s207.cloudy.domain.recommend.learning.dto.LearningItem(l.id, l.thumbnail, s.name, l.title, l.summary, l.duration, l.difficulty, l.link) FROM Learning l " +
            "JOIN LearningService as ls ON ls.learningServicePK.learning.id = l.id " +
            "JOIN Service as s ON ls.learningServicePK.service.id = s.id " +
            "WHERE l.id IN :ids")
    List<LearningItem> findAllByIds(@Param("ids") List<Integer> ids);


}
