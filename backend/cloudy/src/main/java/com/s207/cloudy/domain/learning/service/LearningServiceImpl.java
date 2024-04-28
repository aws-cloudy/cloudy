package com.s207.cloudy.domain.learning.service;

import com.s207.cloudy.domain.learning.dto.LearningItem;
import com.s207.cloudy.domain.learning.dto.LearningListRes;
import com.s207.cloudy.domain.learning.dto.LearningSearchReq;
import com.s207.cloudy.domain.learning.entity.enums.CourseType;
import com.s207.cloudy.domain.learning.entity.enums.DifficultyType;

import com.s207.cloudy.domain.learning.exception.LearningException;
import com.s207.cloudy.domain.learning.repository.JobRepository;
import com.s207.cloudy.domain.learning.repository.LearningRepository;
import com.s207.cloudy.global.error.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LearningServiceImpl implements LearningService {

    private final LearningRepository learningRepository;
    private final JobRepository jobRepository;

    @Override
    public LearningListRes getLearnings(LearningSearchReq learningSearchReq) {
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

        List<LearningItem> items = learningRepository.findLearnings(learningSearchReq);
        return LearningListRes.builder()
                .learningList(items)
                .isModified(false)
                .build();
    }

    @Override
    public LearningListRes getLearningsByJob(int jobId, int count) {
        // 존재하지 않는 직무라면
        if(!jobRepository.existsJobId(jobId)) {
            throw new LearningException(ErrorCode.INVALID_JOB_ID);
        }
        
        List<LearningItem> items = learningRepository.findLearningsByJob(jobId, count);
        return LearningListRes.builder()
                .learningList(items)
                .build();
    }

    @Override
    public LearningListRes getLearningsByJob(int count) {
        List<LearningItem> items = learningRepository.findLearningsByJob(count);
        return LearningListRes.builder()
                .learningList(items)
                .build();
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
