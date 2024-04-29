package com.s207.cloudy.domain.roadmap_group.member.application;

import com.s207.cloudy.domain.members.entity.Member;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapListRes;

public interface MemberRoadmapService {
    RoadmapListRes getRoadmapList(Member member);
}
