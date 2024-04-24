package com.s207.cloudy.domain.learning.repository;

import com.s207.cloudy.domain.learning.dto.LearningItem;
import com.s207.cloudy.domain.learning.dto.LearningSearchReq;

import java.util.List;

//@NoRepositoryBean
public interface LearningRepositoryCustom {
    List<LearningItem> findLearnings(LearningSearchReq learningSearchReq);
}
