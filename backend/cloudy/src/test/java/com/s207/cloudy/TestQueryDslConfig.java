package com.s207.cloudy;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.s207.cloudy.domain.learning.dao.impl.LearningRepositoryImpl;
import com.s207.cloudy.domain.roadmap_group.member.dao.MemberRoadmapQueryRepository;
import com.s207.cloudy.domain.roadmap_group.roadmap.dao.RoadmapQueryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestQueryDslConfig {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public RoadmapQueryRepository roadmapQueryRepository() {
        return new RoadmapQueryRepository(jpaQueryFactory());
    }

    @Bean
    public LearningRepositoryImpl learningRepositoryImpl() { return new LearningRepositoryImpl(jpaQueryFactory());}


    @Bean
    public MemberRoadmapQueryRepository memberRoadmapQueryRepository() {
        return new MemberRoadmapQueryRepository(jpaQueryFactory());
    }

}