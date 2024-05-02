package com.s207.cloudy.domain.roadmap_group.member.dao;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.s207.cloudy.domain.roadmap_group.comment.domain.QRoadmapComment;
import com.s207.cloudy.domain.roadmap_group.member.domain.QMemberRoadmap;
import com.s207.cloudy.domain.roadmap_group.member.dto.BookmarkRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.QRoadmap;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRoadmapQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QMemberRoadmap qMemberRoadmap = QMemberRoadmap.memberRoadmap;
    private static final QRoadmap qRoadmap = QRoadmap.roadmap;
    private static final QRoadmapComment qComment = QRoadmapComment.roadmapComment;

    public Page<BookmarkRes> getRoadmapListByMemberId(String memberId) {

        Pageable pageable = PageRequest.of(0, 1000);
        List<BookmarkRes> content = queryFactory
                .select(Projections.fields(BookmarkRes.class,
                        qMemberRoadmap.id.as("bookmarkId"),
                        qRoadmap.id.as("roadmapId"),
                        qRoadmap.title,
                        qRoadmap.thumbnail,
                        qRoadmap.service,
                        qRoadmap.job,
                        qRoadmap.summary,
                        ExpressionUtils.as(
                                JPAExpressions.select(qComment.count())
                                        .from(qComment)
                                        .where(qComment.roadmap.eq(qRoadmap)),
                                "commentsCnt"
                        ))
                )
                .from(qMemberRoadmap)
                .innerJoin(qMemberRoadmap.roadmap, qRoadmap)
                .where(qMemberRoadmap.memberId.eq(memberId))
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory.from(qRoadmap)
                .select(qRoadmap.count())
                .from(qMemberRoadmap)
                .innerJoin(qMemberRoadmap.roadmap, qRoadmap)
                .where(qMemberRoadmap.memberId.eq(memberId));

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

}


