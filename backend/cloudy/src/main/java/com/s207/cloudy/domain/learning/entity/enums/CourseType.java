package com.s207.cloudy.domain.learning.entity.enums;

import com.s207.cloudy.domain.learning.exception.LearningErrorCode;
import com.s207.cloudy.domain.learning.exception.LearningException;
import com.s207.cloudy.global.handler.exception.CustomValidationException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum CourseType {
    DIGITAL_COURSE("Digital_Course", "Digital Course"),
    DIGITAL_COURSE_WITH_LAB("Digital_Course_With_Lab", "Digital Course With Lab"),
    EXAM_PREPARATION("Exam_Preparation", "Exam Preparation");

    private final String course;
    private final String convertCourse;
    private static final Map<String, String> COURSE_MAP = new HashMap<>();

    static {
        for(CourseType courseType : values()) {
            COURSE_MAP.put(courseType.course, courseType.convertCourse);
        }
    }

    CourseType(String course, String convertCourse) {
        this.course = course;
        this.convertCourse = convertCourse;
    }

    public String getCourse() {
        return course;
    }

    public String getConvertCourse() {
        return convertCourse;
    }

    public static String getByCourse(String course) {
        return Optional.ofNullable(COURSE_MAP.get(course)).orElseThrow(() -> new CustomValidationException());
    }
}
