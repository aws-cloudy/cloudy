package com.s207.cloudy.dummy;

import com.s207.cloudy.domain.roadmap_group.comment.domain.RoadmapComment;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;

public class DummyRoadmapComment {

    public static RoadmapComment getDummyRoadmapComment(Roadmap roadmap, int memberId) {
        return RoadmapComment.builder()
                .roadmap(roadmap)
                .memberId(memberId)
                .content("참 좋은 강의네요!")
                .build();
    }
}
