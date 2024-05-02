package com.s207.cloudy.domain.roadmap_group.roadmap.dao;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.s207.cloudy.domain.roadmap_group.comment.domain.QRoadmapComment;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.QRoadmap;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoadmapQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QRoadmap qRoadmap = QRoadmap.roadmap;
    private static final QRoadmapComment qComment = QRoadmapComment.roadmapComment;

    public Page<RoadmapRes> getRoadmaplist(String job, String service, String query, Pageable pageable) {

        List<RoadmapRes> content = queryFactory
                .select(Projections.fields(RoadmapRes.class,
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
                .where(isSearched(query), isFilteredWithJob(job), isFilteredWithService(service))
                .from(qRoadmap)
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory.from(qRoadmap)
                .select(qRoadmap.count())
                .from(qRoadmap)
                .where(isSearched(query), isFilteredWithJob(job), isFilteredWithService(service));

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    private static BooleanExpression isSearched(String query) {
        if (StringUtils.isNullOrEmpty(query)) {
            return null;
        }

        return qRoadmap.title.contains(query);
    }

    private BooleanExpression isFilteredWithJob(String job) {
        if (StringUtils.isNullOrEmpty(job)) {
            return null;
        }

        return qRoadmap.job.eq((job));
    }

    private BooleanExpression isFilteredWithService(String service) {
        if (StringUtils.isNullOrEmpty(service)) {
            return null;
        }

        return qRoadmap.service.eq((service));
    }


}
