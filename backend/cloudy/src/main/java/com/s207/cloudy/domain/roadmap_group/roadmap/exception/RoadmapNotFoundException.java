package com.s207.cloudy.domain.roadmap_group.roadmap.exception;

public class RoadmapNotFoundException extends RuntimeException{
    public static final String MESSAGE = "존재하지 않는 로드맵입니다.";

    public RoadmapNotFoundException() {
        super(MESSAGE);
    }
}
