package com.s207.cloudy.domain.roadmap_group.member.application;

import com.s207.cloudy.domain.members.domain.Member;
import com.s207.cloudy.domain.roadmap_group.member.dao.MemberRoadmapQueryRepository;
import com.s207.cloudy.domain.roadmap_group.member.dao.MemberRoadmapRepository;
import com.s207.cloudy.domain.roadmap_group.member.domain.MemberRoadmap;
import com.s207.cloudy.domain.roadmap_group.member.dto.BookmarkListRes;
import com.s207.cloudy.domain.roadmap_group.member.dto.BookmarkRes;
import com.s207.cloudy.domain.roadmap_group.member.dto.CreateRoadmapReq;
import com.s207.cloudy.domain.roadmap_group.member.exception.MemberRoadmapException;
import com.s207.cloudy.domain.roadmap_group.roadmap.application.RoadmapService;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import com.s207.cloudy.global.error.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public BookmarkListRes findRoadmapListByMember(Member member) {
        String memberId = member.getUsername();
        Page<BookmarkRes> memberRoadmapList = memberRoadmapQueryRepository.getRoadmapListByMemberId(memberId);
        List<BookmarkRes> bookmarkList = memberRoadmapList.getContent();
        return new BookmarkListRes(bookmarkList, bookmarkList.size());
    }


    @Override
    @Transactional
    public MemberRoadmap createRoadmapBookmark(Member member, CreateRoadmapReq req) {
        if(memberRoadmapRepository.existsByRoadmapIdAndMemberId(member.getId(), req.roadmapId())){
            throw new MemberRoadmapException(ErrorCode.DUPLICATED);
        }

        Roadmap roadmap = roadmapService.findRoadmapEntity(req.roadmapId());

        String memberId = member.getUsername();
        MemberRoadmap memberRoadmap = new MemberRoadmap(memberId, roadmap);

        return memberRoadmapRepository.save(memberRoadmap);
    }

    @Override
    @Transactional
    public void deleteById(int memberRoadmapId) {
        MemberRoadmap memberRoadmap = findMemberRoadmapEntity(memberRoadmapId);
        memberRoadmapRepository.delete(memberRoadmap);
    }

    @Override
    public MemberRoadmap findMemberRoadmapEntity(int memberRoadmapId) {
        return memberRoadmapRepository.findById(memberRoadmapId)
                .orElseThrow(()-> new MemberRoadmapException(ErrorCode.NOT_FOUND));
    }
}
