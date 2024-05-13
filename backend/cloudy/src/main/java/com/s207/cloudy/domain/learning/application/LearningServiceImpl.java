package com.s207.cloudy.domain.learning.application;

import com.s207.cloudy.domain.learning.dao.JobRepository;
import com.s207.cloudy.domain.learning.dao.LearningRepository;
import com.s207.cloudy.domain.learning.domain.Learning;
import com.s207.cloudy.domain.learning.dto.LearningItem;
import com.s207.cloudy.domain.learning.dto.LearningListRes;
import com.s207.cloudy.domain.learning.dto.LearningSearchReq;
import com.s207.cloudy.domain.learning.exception.LearningException;
import com.s207.cloudy.global.error.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LearningServiceImpl implements LearningService {

    private final LearningRepository learningRepository;
    private final JobRepository jobRepository;
    private final ConvertUtil convertUtil;

    @Override
    public LearningListRes getLearnings(LearningSearchReq learningSearchReq) {
        // 난이도 파라미터 변환
        if (learningSearchReq.getDifficulty() != null) {
            String[] covertDifficulty = convertUtil.covertDifficulty(learningSearchReq.getDifficulty());
            learningSearchReq.setDifficulty(covertDifficulty);
        }

        // 강의분류 파라미터 변환
        if (learningSearchReq.getType() != null) {
            String[] covertType = convertUtil.covertType(learningSearchReq.getType());
            learningSearchReq.setType(covertType);
        }

        // 직무명 파라미터 변환
        if (learningSearchReq.getJobName() != null) {
            String[] covertJobName = convertUtil.covertJobName(learningSearchReq.getJobName());
            learningSearchReq.setJobName(covertJobName);
        }

        // 서비스명 파라미터 변환
        if (learningSearchReq.getServiceName() != null) {
            String[] covertServiceName = convertUtil.covertServiceName(learningSearchReq.getServiceName());
            learningSearchReq.setServiceName(covertServiceName);
        }

        List<LearningItem> items = learningRepository.findLearnings(learningSearchReq);
        return LearningListRes.builder()
                .learningList(items)
                .isModified(false)
                .build();
    }

    @Override
    public LearningListRes getLearningsByJob(int jobId, int count) {
        // 존재하지 않는 직무라면
        if (!jobRepository.existsJobId(jobId)) {
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


    @Override
    public List<LearningItem> getCoursesWithRoadmapId(Integer roadmapId) {
        return learningRepository
                .findByRoadmapId(roadmapId)
                .stream()
                .map(Learning::toDto)
                .collect(Collectors.toList());
    }
}
