package com.s207.cloudy.domain.learning.service;

import com.s207.cloudy.domain.learning.dto.LearningItem;
import com.s207.cloudy.domain.learning.dto.LearningListRes;
import com.s207.cloudy.domain.learning.dto.LearningSearchReq;
import com.s207.cloudy.domain.learning.entity.enums.CourseType;
import com.s207.cloudy.domain.learning.entity.enums.DifficultyType;

import com.s207.cloudy.domain.learning.entity.enums.JobNameType;
import com.s207.cloudy.domain.learning.entity.enums.ServiceNameType;
import com.s207.cloudy.domain.learning.exception.LearningException;
import com.s207.cloudy.domain.learning.repository.JobRepository;
import com.s207.cloudy.domain.learning.repository.LearningRepository;
import com.s207.cloudy.global.error.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
        if(learningSearchReq.getJobName() != null) {
            String[] covertJobName = covertJobName(learningSearchReq.getJobName());
            learningSearchReq.setJobName(covertJobName);
        }

        // 서비스명 파라미터 변환
        if(learningSearchReq.getServiceName() != null) {
            String[] covertServiceName = covertServiceName(learningSearchReq.getServiceName());
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

    public String[] covertJobName(String[] jobName) {
        List<String> convertJobName = new ArrayList<>();
        String[] etcArr = new String[] {"Account/Sales Manager", "Alliance Lead", "Business Development/Analyst Manager", "Data Scientist", "Decision Maker", "DevOps Engineer", "Pre-Sales Consultant", "Project/Program/Delivery Manager", "Security Engineer"};

        for(int i=0; i<jobName.length; i++) {
            if(jobName[i].equals("etc")) {
                convertJobName.addAll(List.of(etcArr));
                continue;
            }

            convertJobName.add(JobNameType.getByJobName(jobName[i]));
        }

        return convertJobName.toArray(new String[0]);
    }

    public String[] covertServiceName(String[] serviceName) {
        List<String> convertServiceName = new ArrayList<>();
        String[] etcArr = new String[] {"Data Analytics","Architecting","Business Applications","Cloud Financial Management","Containers","Compute","DevOps","Developer Tools","AWS for Games","Internet of Things (IoT)","Management & Governance","Media Services","Migration & Transfer","Security, Identity & Compliance","SAP on AWS","SaaS","End-user Computing","Developing","Cloud Operations (SysOps)"};

        for(int i=0; i<serviceName.length; i++) {
            if(serviceName[i].equals("etc")) {
                convertServiceName.addAll(List.of(etcArr));
                continue;
            }

            convertServiceName.add(ServiceNameType.getByServiceName(serviceName[i]));
        }

        return convertServiceName.toArray(new String[0]);
    }
}
