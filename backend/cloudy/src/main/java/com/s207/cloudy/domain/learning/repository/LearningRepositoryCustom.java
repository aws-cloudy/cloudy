package com.s207.cloudy.domain.learning.repository;

import com.s207.cloudy.domain.learning.dto.LearningListRes;
import com.s207.cloudy.domain.learning.dto.LearningSearchReq;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

//@NoRepositoryBean
public interface LearningRepositoryCustom {
    List<LearningListRes> findLearnings(LearningSearchReq learningSearchReq);
}
