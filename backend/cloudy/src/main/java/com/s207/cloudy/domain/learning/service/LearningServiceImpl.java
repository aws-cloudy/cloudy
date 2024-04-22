package com.s207.cloudy.domain.learning.service;

import com.s207.cloudy.domain.learning.dto.LearningListRes;
import com.s207.cloudy.domain.learning.repository.LearningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LearningServiceImpl implements LearningService {

    private final LearningRepository learningRepository;

    @Override
    public List<LearningListRes> getLearnings(int page, int pageSize, String[] jobName, String[] serviceName, String[] type, String[] difficulty, String query) {
        return learningRepository.findLearnings(page, pageSize, jobName, serviceName, type, difficulty, query);
    }



}
