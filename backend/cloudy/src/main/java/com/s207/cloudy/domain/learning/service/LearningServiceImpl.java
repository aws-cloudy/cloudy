package com.s207.cloudy.domain.learning.service;

import com.s207.cloudy.domain.learning.dto.LearningListRes;
import com.s207.cloudy.domain.learning.dto.LearningSearchReq;
import com.s207.cloudy.domain.learning.entity.enums.CourseType;
import com.s207.cloudy.domain.learning.entity.enums.DifficultyType;
import com.s207.cloudy.domain.learning.exception.LearningErrorCode;
import com.s207.cloudy.domain.learning.exception.LearningException;
import com.s207.cloudy.domain.learning.repository.LearningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LearningServiceImpl implements LearningService {

    private final LearningRepository learningRepository;

    @Override
    public List<LearningListRes> getLearnings(LearningSearchReq learningSearchReq) {
        // 난이도 파라미터 변환
        if(learningSearchReq.getDifficulty() != null) {
            String[] covertDifficulty = covertDifficulty(learningSearchReq.getDifficulty());
            learningSearchReq.setDifficulty(covertDifficulty);
        }

        // 강의분류 파라미터 변환
        if(learningSearchReq.getType() != null) {
            String[] covertType = covertType(learningSearchReq.getType());
            learningSearchReq.setType(covertType);
        }

        // 직무명 파라미터 변환

        // 서비스명 파라미터 변환

        return learningRepository.findLearnings(learningSearchReq);
    }

    // 유효성 검사
    public boolean isValidParameter(LearningSearchReq learningSearchReq) {

        return true;
    }

    public String[] covertDifficulty(String[] difficulty) {
        String[] convertDifficulty = new String[difficulty.length];

        for(int i=0; i<difficulty.length; i++) {
            convertDifficulty[i] = String.valueOf(DifficultyType.getByCode(difficulty[i]));
        }

        return convertDifficulty;
    }

    public String[] covertType(String[] type) {
        String[] convertType = new String[type.length];

        for(int i=0; i<type.length; i++) {
            convertType[i] = CourseType.getByCourse(type[i]);
        }

        return convertType;
    }
}
