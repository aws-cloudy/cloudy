package com.s207.cloudy.domain.roadmap_group.member.exception;

public class MemberRoadmapNotFoundException extends RuntimeException{
    public static final String MESSAGE = "존재하지 않는 북마크입니다.";

    public MemberRoadmapNotFoundException() {
        super(MESSAGE);
    }
}
