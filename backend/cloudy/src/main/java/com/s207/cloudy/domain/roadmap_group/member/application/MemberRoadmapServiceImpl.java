package com.s207.cloudy.domain.roadmap_group.member.application;

import com.s207.cloudy.domain.members.entity.Member;
import com.s207.cloudy.domain.roadmap_group.member.dao.MemberRoadmapQueryRepository;
import com.s207.cloudy.domain.roadmap_group.roadmap.dao.RoadmapQueryRepository;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapListRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberRoadmapServiceImpl implements MemberRoadmapService {
    private final MemberRoadmapQueryRepository memberRoadmapQueryRepository;
    private final RoadmapQueryRepository roadmapQueryRepository;

    @Override
    public RoadmapListRes getRoadmapList(Member member) {
        String memberId = member.getUsername();
        List<Integer> memberRoadmapList = memberRoadmapQueryRepository.getByMemberId(memberId);
        Page<RoadmapRes> roadmaps = roadmapQueryRepository.findMemberRoadmapList(memberRoadmapList);
        return new RoadmapListRes(roadmaps.getContent(), roadmaps.getTotalPages());

    }
}
