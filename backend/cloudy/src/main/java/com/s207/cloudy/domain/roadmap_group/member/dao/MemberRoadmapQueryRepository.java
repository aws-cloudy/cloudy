package com.s207.cloudy.domain.roadmap_group.member.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.s207.cloudy.domain.roadmap_group.member.domain.QMemberRoadmap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRoadmapQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QMemberRoadmap qMemberRoadmap = QMemberRoadmap.memberRoadmap;

    public List<Integer> getByMemberId(String memberId) {

        return queryFactory
                .select(qMemberRoadmap.roadmap.id)
                .from(qMemberRoadmap)
                .where(qMemberRoadmap.memberId.eq(memberId))
                .fetch();

    }


}
