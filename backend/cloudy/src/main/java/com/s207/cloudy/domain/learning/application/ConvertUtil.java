package com.s207.cloudy.domain.learning.application;

import com.s207.cloudy.domain.learning.domain.enums.CourseType;
import com.s207.cloudy.domain.learning.domain.enums.DifficultyType;
import com.s207.cloudy.domain.learning.domain.enums.JobNameType;
import com.s207.cloudy.domain.learning.domain.enums.ServiceNameType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConvertUtil {

    public String[] covertDifficulty(String[] difficulty) {
        String[] convertDifficulty = new String[difficulty.length];

        for (int i = 0; i < difficulty.length; i++) {
            convertDifficulty[i] = String.valueOf(DifficultyType.getByCode(difficulty[i]));
        }

        return convertDifficulty;
    }

    public String[] covertType(String[] type) {
        String[] convertType = new String[type.length];

        for (int i = 0; i < type.length; i++) {
            convertType[i] = CourseType.getByCourse(type[i]);
        }

        return convertType;
    }

    public String[] covertJobName(String[] jobName) {
        List<String> convertJobName = new ArrayList<>();
        String[] etcArr = new String[] {"Account/Sales Manager", "Alliance Lead",
                "Business Development/Analyst Manager", "Data Scientist", "Decision Maker",
                "DevOps Engineer", "Pre-Sales Consultant", "Project/Program/Delivery Manager",
                "Security Engineer"};

        for (int i = 0; i < jobName.length; i++) {
            if (jobName[i].equals("etc")) {
                convertJobName.addAll(List.of(etcArr));
                continue;
            }

            convertJobName.add(JobNameType.getByJobName(jobName[i]));
        }

        return convertJobName.toArray(new String[0]);
    }

    public String[] covertServiceName(String[] serviceName) {
        List<String> convertServiceName = new ArrayList<>();
        String[] etcArr = new String[] {"Data Analytics", "Architecting", "Business Applications",
                "Cloud Financial Management", "Containers", "Compute", "DevOps", "Developer Tools",
                "AWS for Games", "Internet of Things (IoT)", "Management & Governance",
                "Media Services", "Migration & Transfer", "Security, Identity & Compliance",
                "SAP on AWS", "SaaS", "End-user Computing", "Developing", "Cloud Operations (SysOps)"};

        for (int i = 0; i < serviceName.length; i++) {
            if (serviceName[i].equals("etc")) {
                convertServiceName.addAll(List.of(etcArr));
                continue;
            }

            convertServiceName.add(ServiceNameType.getByServiceName(serviceName[i]));
        }

        return convertServiceName.toArray(new String[0]);
    }
}
