package com.s207.cloudy.domain.learning.service;

import com.s207.cloudy.domain.learning.dto.LearningListRes;
import com.s207.cloudy.domain.learning.dto.LearningSearchReq;
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

    public String[] covertDifficulty(String[] difficulty) {
        String[] convertDifficulty = new String[difficulty.length];

        for(int i=0; i<difficulty.length; i++) {
            switch (difficulty[i]) {
                case "1":
                    convertDifficulty[i] = "Fundamental";
                    break;
                case "2":
                    convertDifficulty[i] = "Intermediate";
                    break;
                case "3":
                    convertDifficulty[i] = "Advanced";
                    break;
                default:
                    // 난이도가 유효한 값이 아니라면
                    throw new LearningException(LearningErrorCode.INVALID_DIFFICULTY);
            }
        }

        return convertDifficulty;
    }

    public String[] covertType(String[] type) {
        String[] convertType = new String[type.length];

        for(int i=0; i<type.length; i++) {
            switch (type[i]) {
                case "Digital_Course", "Digital_Course_With_Lab", "Exam_Preparation":
                    convertType[i] = type[i].replace("_", " ");
                    break;
                default:
                    // 강의분류가 유효한 값이 아니라면
                    throw new LearningException(LearningErrorCode.INVALID_TYPE);
            }
        }

        return convertType;
    }
}
