package com.s207.cloudy.domain.roadmap_group.member.application;

import com.s207.cloudy.domain.members.entity.Member;
import com.s207.cloudy.domain.roadmap_group.member.dao.MemberRoadmapQueryRepository;
import com.s207.cloudy.domain.roadmap_group.member.dao.MemberRoadmapRepository;
import com.s207.cloudy.domain.roadmap_group.member.domain.MemberRoadmap;
import com.s207.cloudy.domain.roadmap_group.member.dto.CreateRoadmapReq;
import com.s207.cloudy.domain.roadmap_group.roadmap.application.RoadmapService;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRoadmapServiceImpl implements MemberRoadmapService {
    private final MemberRoadmapQueryRepository memberRoadmapQueryRepository;
    private final RoadmapService roadmapService;
    private final MemberRoadmapRepository memberRoadmapRepository;

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

    @Override
    @Transactional
    public MemberRoadmap createRoadmapBookmark(Member member, CreateRoadmapReq req) {
        Roadmap roadmap = roadmapService.findRoadmapEntity(req.getRoadmapId());

        String memberId = member.getUsername();
        MemberRoadmap memberRoadmap = new MemberRoadmap(memberId, roadmap);

        return memberRoadmapRepository.save(memberRoadmap);
    }

}
