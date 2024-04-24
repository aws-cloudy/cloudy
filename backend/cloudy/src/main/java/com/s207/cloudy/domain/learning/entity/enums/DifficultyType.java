package com.s207.cloudy.domain.learning.entity.enums;

import com.s207.cloudy.domain.learning.exception.LearningErrorCode;
import com.s207.cloudy.domain.learning.exception.LearningException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum DifficultyType {
    FUNDAMENTAL("1"),
    INTERMEDIATE("2"),
    ADVANCED("3");

    private String code;
    private static final Map<String, DifficultyType> CODE_MAP = new HashMap<>();

    static {
        for(DifficultyType difficultyType : values()) {
            CODE_MAP.put(difficultyType.code, difficultyType);
        }
    }

    DifficultyType(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public static DifficultyType getByCode(String code) {
        return Optional.ofNullable(CODE_MAP.get(code)).orElseThrow(() -> new LearningException(LearningErrorCode.INVALID_DIFFICULTY));
    }
}
