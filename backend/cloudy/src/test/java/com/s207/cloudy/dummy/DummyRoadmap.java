package com.s207.cloudy.dummy;

import com.s207.cloudy.domain.roadmapGroup.roadmap.domain.Roadmap;
import com.s207.cloudy.domain.roadmapGroup.roadmap.dto.RoadmapRes;

public class DummyRoadmap {

    public static Roadmap getDummyRoadmap() {
        return Roadmap.builder()
                .title("AWS Skill Builder Learner Guide")
                .thumbnail("16825")
                .service("GeneratedAI")
                .job("DataWrangler")
                .summary("효과적인 프롬포트를 설계하기 위한 원칙, 기법 및 모범 사례를 알 수 있음 효과적인 프롬포트를 설계하기 위한 원칙, 기법 및 모범 사례를 어찌고")
                .build();
    }

    public static RoadmapRes getDummyRoadmapRes(Roadmap roadmap) {
        return new RoadmapRes(roadmap.getId(),
                roadmap.getTitle(),
                roadmap.getThumbnail(),
                roadmap.getService(),
                roadmap.getJob(),
                roadmap.getSummary(),
                2);
    }
}
