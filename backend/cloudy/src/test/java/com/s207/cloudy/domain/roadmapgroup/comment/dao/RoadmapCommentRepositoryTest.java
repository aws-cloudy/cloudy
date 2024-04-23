package com.s207.cloudy.domain.roadmapgroup.comment.dao;

import com.s207.cloudy.domain.roadmapgroup.comment.domain.RoadmapComment;
import com.s207.cloudy.domain.roadmapgroup.roadmap.domain.Roadmap;
import com.s207.cloudy.domain.roadmapgroup.roadmap.dto.RoadmapListRes;
import com.s207.cloudy.dummy.DummyRoadmap;
import com.s207.cloudy.dummy.DummyRoadmapComment;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoadmapCommentRepositoryTest {

    @Autowired
    RoadmapCommentRepository roadmapCommentRepository;

    @Autowired
    TestEntityManager entityManager;

    Roadmap dummyRoadmap1;
    RoadmapComment dummyRoadmapComment1;
    RoadmapComment dummyRoadmapComment2;
    @BeforeEach
    void setUp() {

        dummyRoadmap1 = DummyRoadmap.getDummyRoadmap();
        entityManager.persist(dummyRoadmap1);

        dummyRoadmapComment1 = DummyRoadmapComment.getDummyRoadmapComment(dummyRoadmap1, 1);
        dummyRoadmapComment2 = DummyRoadmapComment.getDummyRoadmapComment(dummyRoadmap1, 1);
        entityManager.persist(dummyRoadmapComment1);
        entityManager.persist(dummyRoadmapComment2);

        entityManager.flush();
        entityManager.clear();
    }

    @DisplayName("로드맵별 전체 댓글 리스트를 정상적으로 조회한다.")
    @Test
    void findAllSuccess() {
        List<RoadmapComment> commentList = roadmapCommentRepository.findByRoadmap(dummyRoadmap1);
        SoftAssertions.assertSoftly(assertions -> {
            assertions.assertThat(commentList).isNotEmpty();
            assertions.assertThat(commentList).hasSize(2);
            assertions.assertThat(commentList.get(0).getRoadmap().getId()).isEqualTo(dummyRoadmap1.getId());
            assertions.assertThat(commentList.get(0).getId()).isEqualTo(dummyRoadmapComment1.getId());
            assertions.assertThat(commentList.get(0).getMemberId()).isEqualTo(dummyRoadmapComment1.getMemberId());
            assertions.assertThat(commentList.get(0).getContent()).isEqualTo(dummyRoadmapComment1.getContent());
        });
    }

}