package com.s207.cloudy.domain.recommend.learning.domain.enums;

import com.s207.cloudy.global.error.exception.CustomValidationException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum JobNameType {
    ARCHITECT("Architect", "Architect"),
    BUSINESS_USER("Business_User", "Business User"),
    CLOUD_OPERATOR("Cloud_Operator", "Cloud Operator"),
    DATA_ENGINEER("Data_Engineer", "Data Engineer"),
    DEVELOPER("Developer", "Developer"),
    INFRASTRUCTURE_ENGINEER("Infrastructure_Engineer", "Infrastructure Engineer"),
    ETC("etc", "etc");

    private final String jobName;
    private final String convertJobName;
    private static final Map<String, String> JOBNAME_MAP = new HashMap<>();

    static {
        for(JobNameType jobNameType : values()) {
            JOBNAME_MAP.put(jobNameType.jobName, jobNameType.convertJobName);
        }
    }

    JobNameType(String jobName, String convertJobName) {
        this.jobName = jobName;
        this.convertJobName = convertJobName;
    }

    public String getJobName() {
        return jobName;
    }

    public String getConvertJobName() {
        return convertJobName;
    }

    public static String getByJobName(String jobName) {
        return Optional.ofNullable(JOBNAME_MAP.get(jobName)).orElseThrow(CustomValidationException::new);
    }
}
