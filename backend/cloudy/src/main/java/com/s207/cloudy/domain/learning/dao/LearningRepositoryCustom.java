package com.s207.cloudy.domain.learning.dao;

import com.s207.cloudy.domain.learning.dto.LearningItem;
import com.s207.cloudy.domain.learning.dto.LearningSearchReq;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface LearningRepositoryCustom {
    List<LearningItem> findLearnings(LearningSearchReq learningSearchReq);

    List<LearningItem> findLearningsByJob(int jobId, int count);
    List<LearningItem> findLearningsByJob(int count);

}
