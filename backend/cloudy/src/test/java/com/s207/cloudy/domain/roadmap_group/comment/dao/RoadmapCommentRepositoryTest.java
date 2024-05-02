package com.s207.cloudy.domain.roadmap_group.comment.dao;

import com.s207.cloudy.TestQueryDslConfig;
import com.s207.cloudy.domain.members.entity.Member;
import com.s207.cloudy.domain.roadmap_group.comment.domain.RoadmapComment;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import com.s207.cloudy.dummy.DummyRoadmap;
import com.s207.cloudy.dummy.DummyRoadmapComment;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@Import(TestQueryDslConfig.class)
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


        Member member1 = Member.builder().id("test").name("testName").build();
        Member member2 = Member.builder().id("test1").name("testName1").build();

        entityManager.persist(member1);
        entityManager.persist(member2);


        dummyRoadmapComment1 = DummyRoadmapComment.getDummyRoadmapComment(dummyRoadmap1, member1);
        dummyRoadmapComment2 = DummyRoadmapComment.getDummyRoadmapComment(dummyRoadmap1, member2);
        entityManager.persist(dummyRoadmapComment1);
        entityManager.persist(dummyRoadmapComment2);

        entityManager.flush();
        entityManager.clear();
    }

    @DisplayName("로드맵별 전체 댓글 리스트를 정상적으로 조회한다.")
    @Test
    void findAllSuccess() {
        List<RoadmapComment> commentList = roadmapCommentRepository.findByRoadmapId(dummyRoadmap1.getId());

        SoftAssertions.assertSoftly(assertions -> {
            assertions.assertThat(commentList).isNotEmpty();
            assertions.assertThat(commentList).hasSize(2);
            assertions.assertThat(commentList.get(0).getRoadmap().getId()).isEqualTo(dummyRoadmap1.getId());
            assertions.assertThat(commentList.get(0).getId()).isEqualTo(dummyRoadmapComment1.getId());
            assertions.assertThat(commentList.get(0).getMember().getId()).isEqualTo(dummyRoadmapComment1.getMember().getId());
            assertions.assertThat(commentList.get(0).getContent()).isEqualTo(dummyRoadmapComment1.getContent());
        });
    }

}