package com.s207.cloudy.domain.roadmap_group.member.application;

import com.s207.cloudy.domain.members.entity.Member;
import com.s207.cloudy.domain.roadmap_group.member.domain.MemberRoadmap;
import com.s207.cloudy.domain.roadmap_group.member.dto.BookmarkListRes;
import com.s207.cloudy.domain.roadmap_group.member.dto.CreateRoadmapReq;

public interface MemberRoadmapService {
    BookmarkListRes findRoadmapListByMember(Member member);

    MemberRoadmap createRoadmapBookmark(Member member, CreateRoadmapReq req);

    void deleteById(int memberRoadmapId);
}
