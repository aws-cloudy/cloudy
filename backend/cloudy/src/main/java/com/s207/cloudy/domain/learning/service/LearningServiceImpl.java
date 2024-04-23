package com.s207.cloudy.domain.learning.service;

import com.s207.cloudy.domain.learning.dto.LearningListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LearningServiceImpl implements LearningService {

//    private final LearningRepository learningRepository;

    @Override
    public List<LearningListRes> getLearnings() {
        return null;
    }
}
