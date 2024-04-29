package com.s207.cloudy.domain.learning.service;

import com.s207.cloudy.domain.learning.dto.LearningListRes;
import com.s207.cloudy.domain.learning.dto.LearningSearchReq;


public interface LearningService {
    LearningListRes getLearnings(LearningSearchReq learningSearchReq);

    LearningListRes getLearningsByJob(int jobId, int count);
    LearningListRes getLearningsByJob(int count);
}
