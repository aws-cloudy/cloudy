package com.s207.cloudy.domain.learning.repository;

import com.s207.cloudy.domain.learning.dto.LearningListRes;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

//@NoRepositoryBean
public interface LearningRepositoryCustom {
    List<LearningListRes> findLearnings(int page, int pageSize, String[] jobName, String[] serviceName, String[] type, String[] difficulty, String query);
}
