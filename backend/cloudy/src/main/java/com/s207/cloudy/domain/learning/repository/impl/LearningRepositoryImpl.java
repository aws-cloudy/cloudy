package com.s207.cloudy.domain.learning.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.s207.cloudy.domain.learning.dto.LearningItem;
import com.s207.cloudy.domain.learning.dto.LearningSearchReq;
import com.s207.cloudy.domain.learning.repository.LearningRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.s207.cloudy.domain.learning.entity.QJob.job;
import static com.s207.cloudy.domain.learning.entity.QLearning.learning;
import static com.s207.cloudy.domain.learning.entity.QLearningJob.learningJob;
import static com.s207.cloudy.domain.learning.entity.QLearningService.learningService;
import static com.s207.cloudy.domain.learning.entity.QService.service;

@RequiredArgsConstructor
@Repository
public class LearningRepositoryImpl implements LearningRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private BooleanBuilder getSearchOption(LearningSearchReq learningSearchReq) {
        BooleanBuilder searchOptions = new BooleanBuilder();

        // 제목에 대한 필터링
        String query = learningSearchReq.getQuery();
        if(query != null && !query.trim().isEmpty()) {
            searchOptions.and(learning.title.like("%" + query + "%"));
        }

        // 유형에 대한 필터링
        String[] type = learningSearchReq.getType();
        if (type != null && type.length > 0) {
            searchOptions.and(learning.type.in(type));
        }

        // 난이도에 대한 필터링
        String[] difficulty = learningSearchReq.getDifficulty();
        if (difficulty != null && difficulty.length > 0) {
            searchOptions.and(learning.difficulty.in(difficulty));
        }

        // 직무명에 대한 필터링
        String[] jobName = learningSearchReq.getJobName();
        if (jobName != null && jobName.length > 0) {
            searchOptions.and(
                    learning.id.in(
                            JPAExpressions.selectDistinct(learningJob.learningJobPK.learning.id)
                                    .from(learningJob)
                                    .where(
                                            learningJob.learningJobPK.job.id.in(
                                                    JPAExpressions.select(job.id)
                                                            .from(job)
                                                            .where(
                                                                    job.name.in(jobName)
                                                            )
                                            )
                                    )
                    )
            );
        }

        // 서비스명에 대한 필터링
        String[] serviceName = learningSearchReq.getServiceName();
        if (serviceName != null && serviceName.length > 0) {
            searchOptions.and(
                    learning.id.in(
                            JPAExpressions.select(learningService.learningServicePK.learning.id)
                                    .from(learningService)
                                    .where(
                                            learningService.learningServicePK.service.id.in(
                                                    JPAExpressions.select(service.id)
                                                            .from(service)
                                                            .where(
                                                                    service.type.in(serviceName)
                                                            )
                                            )
                                    )
                    )
            );
        }

        return searchOptions;
    }

    Expression<?>[] learningFields = {
            learning.id.as("learningId"), learning.thumbnail, learning.title, learning.summary,
            learning.duration, learning.difficulty, learning.link, service.type.as("serviceType")
    };

    @Override
    public List<LearningItem> findLearnings(LearningSearchReq learningSearchReq) {
        int page = learningSearchReq.getPage();
        int pageSize = learningSearchReq.getPageSize();

        BooleanBuilder searchOptions = getSearchOption(learningSearchReq);

        JPAQuery<LearningItem> jpaQuery = queryFactory
                .select(Projections.fields(LearningItem.class,learningFields))
                .from(learning)
                .leftJoin(learningService).on(learning.id.eq(learningService.learningServicePK.learning.id))
                .leftJoin(service).on(learningService.learningServicePK.service.id.eq(service.id))
                .where(searchOptions);

        // 페이지네이션 설정
        jpaQuery.orderBy(learning.id.asc())
                .offset((long)((page - 1) * pageSize))
                .limit(pageSize);

        return jpaQuery.fetch();
    }

    @Override
    public List<LearningItem> findLearningsByJob(int jobId, int count) {
        return queryFactory
                .select(Projections.fields(LearningItem.class, learningFields))
                .from(learning)
                .leftJoin(learningService).on(learning.id.eq(learningService.learningServicePK.learning.id))
                .leftJoin(service).on(learningService.learningServicePK.service.id.eq(service.id))
                .where(
                        learning.id.in(
                                JPAExpressions.select(learningJob.learningJobPK.learning.id)
                                        .from(learningJob)
                                        .where(
                                                learningJob.learningJobPK.job.id.eq(jobId)
                                        )
                                )
                )
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .limit(count)
                .fetch();
    }

    @Override
    public List<LearningItem> findLearningsByJob(int count) {
        return queryFactory
                .select(Projections.fields(LearningItem.class, learningFields))
                .from(learning)
                .leftJoin(learningService).on(learning.id.eq(learningService.learningServicePK.learning.id))
                .leftJoin(service).on(learningService.learningServicePK.service.id.eq(service.id))
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .limit(count)
                .fetch();
    }
}
