package com.s207.cloudy.domain.learning.dao.impl;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.s207.cloudy.domain.learning.dao.LearningRepositoryCustom;
import com.s207.cloudy.domain.learning.dto.LearningItem;
import com.s207.cloudy.domain.learning.dto.LearningSearchReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.s207.cloudy.domain.learning.domain.QJob.job;
import static com.s207.cloudy.domain.learning.domain.QLearning.learning;
import static com.s207.cloudy.domain.learning.domain.QLearningJob.learningJob;
import static com.s207.cloudy.domain.learning.domain.QLearningService.learningService;
import static com.s207.cloudy.domain.learning.domain.QService.service;

@RequiredArgsConstructor
@Repository
public class LearningRepositoryImpl implements LearningRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    Expression<?>[] learningFields = {
            learning.id.as("learningId"), learning.thumbnail, learning.title, learning.summary,
            learning.duration, learning.difficulty, learning.link, service.type.as("serviceType")
    };

    @Override
    public List<LearningItem> findLearnings(LearningSearchReq learningSearchReq) {
        int page = learningSearchReq.getPage();
        int pageSize = learningSearchReq.getPageSize();

        JPAQuery<LearningItem> jpaQuery = queryFactory
                .select(Projections.fields(LearningItem.class, learningFields))
                .from(learning)
                .leftJoin(learningService).on(learning.id.eq(learningService.learningServicePK.learning.id))
                .leftJoin(service).on(learningService.learningServicePK.service.id.eq(service.id))
                .where(filteredWithTitle(learningSearchReq.getQuery()),
                        filteredWithType(learningSearchReq.getType()),
                        filteredWithDifficulty(learningSearchReq.getDifficulty()),
                        filteredWithJobName(learningSearchReq.getJobName()),
                        filteredWithServiceName(learningSearchReq.getServiceName())
                );

        // 페이지네이션 설정
        jpaQuery.orderBy(learning.id.asc())
                .offset(((long) (page - 1) * pageSize))
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

    /**
     * 제목에 대한 필터링
     *
     * @param query 필터링 할 제목
     * @return
     */
    public Predicate filteredWithTitle(String query) {
        if (query == null || query.trim().isEmpty()) {
            return null;
        }

        return learning.title.like("%" + query + "%");
    }

    /**
     * 유형에 대한 필터링
     *
     * @param type 필터링 할 유형
     * @return
     */
    public Predicate filteredWithType(String[] type) {
        if (isInvalidParameter(type)) {
            return null;
        }

        return learning.type.in(type);
    }

    /**
     * 서비스명에 대한 필터링
     *
     * @param serviceName 필터링 할 서비스
     * @return
     */
    private Predicate filteredWithServiceName(String[] serviceName) {
        if (isInvalidParameter(serviceName)) {
            return null;
        }
        return learning.id.in(
                JPAExpressions.select(learningService.learningServicePK.learning.id)
                        .from(learningService)
                        .where(
                                learningService.learningServicePK.service.id.in(
                                        JPAExpressions.select(service.id)
                                                .from(service)
                                                .where(service.type.in(serviceName))
                                )
                        )
        );
    }

    /**
     * 직무명에 대한 필터링
     *
     * @param jobName 필터링 할 직무
     * @return
     */
    private Predicate filteredWithJobName(String[] jobName) {
        if (isInvalidParameter(jobName)) {
            return null;
        }
        return learning.id.in(
                JPAExpressions.selectDistinct(learningJob.learningJobPK.learning.id)
                        .from(learningJob)
                        .where(
                                learningJob.learningJobPK.job.id.in(
                                        JPAExpressions.select(job.id)
                                                .from(job)
                                                .where(job.name.in(jobName))
                                )
                        )
        );
    }

    /**
     * 난이도에 대한 필터링
     *
     * @param difficulty 필터링 할 난이도
     * @return
     */
    private Predicate filteredWithDifficulty(String[] difficulty) {
        if (isInvalidParameter(difficulty)) {
            return null;
        }
        return learning.difficulty.in(difficulty);
    }

    private boolean isInvalidParameter(String[] arr) {
        return arr == null || arr.length == 0;
    }
}
