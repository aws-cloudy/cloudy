package com.s207.cloudy.domain.learning.dao;

import com.s207.cloudy.TestQueryDslConfig;
import com.s207.cloudy.domain.learning.dto.LearningItem;
import com.s207.cloudy.domain.learning.dto.LearningSearchReq;
import com.s207.cloudy.domain.learning.entity.Learning;
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

    Learning dummy1;
    Learning dummy2;

    @BeforeEach
    void setUp() {
        dummy1 = DummyLearning.getDummyLearning1();
        dummy2 = DummyLearning.getDummyLearning2();

        entityManager.persist(dummy1);
        entityManager.persist(dummy2);

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
}
