package com.s207.cloudy.domain.learning.dao;

import com.s207.cloudy.TestQueryDslConfig;
import com.s207.cloudy.domain.learning.dto.LearningItem;
import com.s207.cloudy.domain.learning.dto.LearningSearchReq;
import com.s207.cloudy.domain.learning.entity.Job;
import com.s207.cloudy.domain.learning.entity.Learning;
import com.s207.cloudy.domain.learning.entity.LearningJob;
import com.s207.cloudy.domain.learning.repository.impl.LearningRepositoryImpl;
import com.s207.cloudy.dummy.learning.DummyLearning;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

@DataJpaTest(showSql = true)
@Import(TestQueryDslConfig.class)
public class LearningRepostioryImplTest {

    @Autowired
    LearningRepositoryImpl learningRepository;

    @Autowired
    TestEntityManager entityManager;

    Learning dummy1, dummy2, dummy3, dummy4;
    Job job;
    LearningJob learningJob3, learningJob4;

    @BeforeEach
    void setUp() {
        dummy1 = DummyLearning.getDummyLearning1();
        dummy2 = DummyLearning.getDummyLearning2();
        dummy3 = DummyLearning.getDummyLearning3();
        dummy4 = DummyLearning.getDummyLearning4();

        entityManager.persist(dummy1);
        entityManager.persist(dummy2);
        entityManager.persist(dummy3);
        entityManager.persist(dummy4);

        job = DummyLearning.getDummyJob();
        entityManager.persist(job);

        learningJob3 = DummyLearning.getDummyLearningJob(dummy3, job);
        learningJob4 = DummyLearning.getDummyLearningJob(dummy4, job);
        entityManager.persist(learningJob3);
        entityManager.persist(learningJob4);
        
        entityManager.flush();
        entityManager.clear();
    }

    @DisplayName("검색어 수정전의 전체 학습 리스트를 정상적으로 조회한다.")
    @Test
    void findList() {
        // given
        LearningSearchReq req = new LearningSearchReq();
        req.setPageSize(2);

        // when
        List<LearningItem> list = learningRepository.findLearnings(req);

        // then
        SoftAssertions.assertSoftly(assertions -> {
            assertions.assertThat(list).isNotEmpty();
            assertions.assertThat(list).hasSize(2);
            assertions.assertThat(list.get(0).getLearningId()).isEqualTo(dummy1.getId());
            assertions.assertThat(list.get(0).getThumbnail()).isEqualTo(dummy1.getThumbnail());
            assertions.assertThat(list.get(0).getTitle()).isEqualTo(dummy1.getTitle());
            assertions.assertThat(list.get(0).getSummary()).isEqualTo(dummy1.getSummary());
            assertions.assertThat(list.get(0).getDuration()).isEqualTo(dummy1.getDuration());
            assertions.assertThat(list.get(0).getDifficulty()).isEqualTo(dummy1.getDifficulty());
            assertions.assertThat(list.get(0).getLink()).isEqualTo(dummy1.getLink());
        });
    }

    @DisplayName("로그인 시, 지정한 개수만큼 해당 직무번호와 일치하는 전체 학습 목록을 랜덤하게 조회한다.")
    @Test
    void findLearningsByJobWhenLogin() {
        // given
        int jobId = job.getId();
        int count = 2;

        // when
        List<LearningItem> list = learningRepository.findLearningsByJob(jobId, count);

        // then
        SoftAssertions.assertSoftly(assertions -> {
            assertions.assertThat(list).isNotEmpty();
            assertions.assertThat(list).hasSize(2);
        });
    }

    @DisplayName("로그아웃 시, 지정한 개수만큼 전체 학습 목록을 랜덤하게 조회한다.")
    @Test
    void findLearningsByJobWhenLogout() {
        // given
        int count = 2;

        // when
        List<LearningItem> list = learningRepository.findLearningsByJob(count);

        // then
        SoftAssertions.assertSoftly(assertions -> {
            assertions.assertThat(list).isNotEmpty();
            assertions.assertThat(list).hasSize(2);
        });
    }
}
