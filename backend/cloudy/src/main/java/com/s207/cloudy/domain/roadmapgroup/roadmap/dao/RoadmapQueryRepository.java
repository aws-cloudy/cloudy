package com.s207.cloudy.domain.roadmapgroup.roadmap.dao;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.s207.cloudy.domain.roadmapgroup.comment.domain.QRoadmapComment;
import com.s207.cloudy.domain.roadmapgroup.roadmap.domain.QRoadmap;
import com.s207.cloudy.domain.roadmapgroup.roadmap.dto.RoadmapListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoadmapQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static QRoadmap qRoadmap = QRoadmap.roadmap;
    private static QRoadmapComment qComment = QRoadmapComment.roadmapComment;

    public Page<RoadmapListRes> findRoadmapList(String filter, String value, Pageable pageable) {

        List<RoadmapListRes> content = queryFactory.selectFrom(qRoadmap)
                .select(Projections.fields(RoadmapListRes.class,
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
                .where(isFiltered(filter, value))
                .fetch();

        Long count = queryFactory.selectFrom(qRoadmap)
                .select(qRoadmap.id.count())
                .where(isFiltered(filter, value))
                .fetchOne();

        return new PageImpl<>(content, pageable, count);

    }

    private BooleanExpression isFiltered(String filter, String value) {
        if (StringUtils.isNullOrEmpty(filter)) {
            return null;
        }

        if (filter.equals("job")) {
            return qRoadmap.job.eq((value));
        }

        if (filter.equals("service")) {
            return qRoadmap.service.eq((value));
        }

        return null;
    }

}
