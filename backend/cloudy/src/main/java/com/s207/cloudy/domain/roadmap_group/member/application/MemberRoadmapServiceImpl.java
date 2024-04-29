package com.s207.cloudy.domain.roadmap_group.member.application;

import com.s207.cloudy.domain.members.entity.Member;
import com.s207.cloudy.domain.roadmap_group.member.dao.MemberRoadmapQueryRepository;
import com.s207.cloudy.domain.roadmap_group.roadmap.application.RoadmapService;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberRoadmapServiceImpl implements MemberRoadmapService {
    private final MemberRoadmapQueryRepository memberRoadmapQueryRepository;
    private final RoadmapService roadmapService;

    @Override
    public RoadmapListRes findRoadmapListByMember(Member member) {
        String memberId = member.getUsername();
        List<Integer> memberRoadmapList = findRoadmapIdList(memberId);
        return roadmapService.findMemberRoadmapList(memberRoadmapList);
    }

    @Override
    public List<Integer> findRoadmapIdList(String memberId) {
        return memberRoadmapQueryRepository.getRoadmapIdListByMemberId(memberId);
    }

}
