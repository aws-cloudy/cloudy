package com.s207.cloudy.domain.learning.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.s207.cloudy.domain.learning.dto.LearningListRes;
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

    // 서비스명에 대한 필터링 나중에 추가해야!!
    @Override
    public List<LearningListRes> findLearnings(LearningSearchReq learningSearchReq) {
        int page = learningSearchReq.getPage();
        int pageSize = learningSearchReq.getPageSize();
        String[] jobName = learningSearchReq.getJobName();
//        String[] serviceName = learningSearchReq.getServiceName();
        String[] type = learningSearchReq.getType();
        String[] difficulty = learningSearchReq.getDifficulty();
        String query = learningSearchReq.getQuery();

        JPAQuery<LearningListRes> jpaQuery = queryFactory.select(Projections.fields(LearningListRes.class,
                        learning.id.as("learningId"), learning.thumbnail, learning.title,
                        learning.summary, learning.duration, learning.difficulty, learning.link, service.type.as("serviceType")))
                .from(learning)
                .leftJoin(learningService).on(learning.id.eq(learningService.learningServicePK.learning.id))
                .leftJoin(service).on(learningService.learningServicePK.service.id.eq(service.id));

        // 제목에 대한 필터링
        if(query != null && !query.isEmpty()) {
            jpaQuery.where(learning.title.like("%" + query + "%"));
        }

        // 유형에 대한 필터링
        if (type != null && type.length > 0) {
            jpaQuery.where(learning.type.in(type));
        }

        // 난이도에 대한 필터링
        if (difficulty != null && difficulty.length > 0) {
            jpaQuery.where(learning.difficulty.in(difficulty));
        }

        // 직무명에 대한 필터링
        if (jobName != null && jobName.length > 0) {
            jpaQuery.where(
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

        // 페이지네이션 설정
        jpaQuery.orderBy(learning.id.asc())
                .offset((page - 1) * pageSize)
                .limit(pageSize);

        return jpaQuery.fetch();
    }

}