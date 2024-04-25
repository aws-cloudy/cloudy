package com.s207.cloudy.domain.learning.repository;

import com.s207.cloudy.domain.learning.dto.LearningItem;
import com.s207.cloudy.domain.learning.dto.LearningSearchReq;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//@NoRepositoryBean
public interface LearningRepositoryCustom {
    List<LearningItem> findLearnings(LearningSearchReq learningSearchReq);

    List<LearningItem> findLearningsByJob(int JobId, int count);
    List<LearningItem> findLearningsByJob(int count);

}
