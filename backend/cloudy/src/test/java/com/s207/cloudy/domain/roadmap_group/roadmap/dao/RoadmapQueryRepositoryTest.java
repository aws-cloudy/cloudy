package com.s207.cloudy.domain.roadmap_group.roadmap.dao;

import com.s207.cloudy.TestQueryDslConfig;
import com.s207.cloudy.domain.members.domain.Member;
import com.s207.cloudy.domain.roadmap_group.comment.domain.RoadmapComment;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapRes;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

@DataJpaTest
@Import(TestQueryDslConfig.class)
class RoadmapQueryRepositoryTest {

    @Autowired
    RoadmapQueryRepository roadmapQueryRepository;

    @Autowired
    TestEntityManager entityManager;

    Roadmap dummyRoadmap1;
    Roadmap dummyRoadmap2;
    RoadmapComment dummyRoadmapComment1;
    RoadmapComment dummyRoadmapComment2;

    @BeforeEach
    void setUp() {

        dummyRoadmap1 = DummyRoadmap.getDummyRoadmap();
        dummyRoadmap2 = DummyRoadmap.getDummyRoadmap();
        ReflectionTestUtils.setField(dummyRoadmap2, "job", "Backend Developer");
        ReflectionTestUtils.setField(dummyRoadmap2, "service", "AWS EC2");

        entityManager.persist(dummyRoadmap1);
        entityManager.persist(dummyRoadmap2);

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

    @DisplayName("전체 로드맵 리스트를 정상적으로 조회한다.")
    @Test
    void get_roadmap_list() {
        Page<RoadmapRes> roadmapList = roadmapQueryRepository.getRoadmaplist(null, null, null, PageRequest.of(0, 10));
        List<RoadmapComment> actualCommentCnt = List.of(dummyRoadmapComment1, dummyRoadmapComment2);
        SoftAssertions.assertSoftly(assertions -> {
            assertions.assertThat(roadmapList).isNotEmpty();
            assertions.assertThat(roadmapList).hasSize(2);
            assertions.assertThat(roadmapList.getTotalPages()).isEqualTo(1);
            assertions.assertThat(roadmapList.getContent().get(0).roadmapId()).isEqualTo(dummyRoadmap1.getId());
            assertions.assertThat(roadmapList.getContent().get(0).title()).isEqualTo(dummyRoadmap1.getTitle());
            assertions.assertThat(roadmapList.getContent().get(0).thumbnail()).isEqualTo(dummyRoadmap1.getThumbnail());
            assertions.assertThat(roadmapList.getContent().get(0).summary()).isEqualTo(dummyRoadmap1.getSummary());
            assertions.assertThat(roadmapList.getContent().get(0).commentsCnt()).isEqualTo(actualCommentCnt.size());
        });
    }

    @DisplayName("전체 로드맵 리스트 사이즈가 2개이고, 페이지 사이즈가 1일 때 페이지네이션이 정상적으로 적용된다.")
    @Test
    void get_roadmap_list_with_pagination() {
        Page<RoadmapRes> roadmapList = roadmapQueryRepository.getRoadmaplist(null, null, null, PageRequest.of(0, 1));
        SoftAssertions.assertSoftly(assertions -> {
            assertions.assertThat(roadmapList).isNotNull();
            assertions.assertThat(roadmapList).hasSize(1);
            assertions.assertThat(roadmapList.getTotalPages()).isEqualTo(2);
            assertions.assertThat(roadmapList.getContent().get(0).roadmapId()).isEqualTo(dummyRoadmap1.getId());
        });
    }

    @DisplayName("직무명으로 필터링이 정상적으로 동작한다.")
    @Test
    void get_roadmap_list_by_roadmap_job() {
        Page<RoadmapRes> roadmapList = roadmapQueryRepository.getRoadmaplist("Backend Developer", null, null, PageRequest.of(0, 10));
        SoftAssertions.assertSoftly(assertions -> {
            assertions.assertThat(roadmapList).isNotNull();
            assertions.assertThat(roadmapList).hasSize(1);
            assertions.assertThat(roadmapList.getContent().get(0).roadmapId()).isEqualTo(dummyRoadmap2.getId());
            assertions.assertThat(roadmapList.getContent().get(0).job()).isEqualTo(dummyRoadmap2.getJob());
        });
    }

    @DisplayName("서비스명으로 필터링이 정상적으로 동작한다.")
    @Test
    void get_roadmap_list_by_roadmap_service() {
        Page<RoadmapRes> roadmapList = roadmapQueryRepository.getRoadmaplist(null, "AWS EC2", null, PageRequest.of(0, 10));
        SoftAssertions.assertSoftly(assertions -> {
            assertions.assertThat(roadmapList).isNotNull();
            assertions.assertThat(roadmapList).hasSize(1);
            assertions.assertThat(roadmapList.getContent().get(0).roadmapId()).isEqualTo(dummyRoadmap2.getId());
            assertions.assertThat(roadmapList.getContent().get(0).service()).isEqualTo(dummyRoadmap2.getService());
        });
    }

    @DisplayName("로드맵명으로 검색이 정상적으로 동작한다.")
    @Test
    void get_roadmap_list_by_roadmap_name() {
        Page<RoadmapRes> roadmapList = roadmapQueryRepository.getRoadmaplist(null, null, "Learner Guide", PageRequest.of(0, 10));
        SoftAssertions.assertSoftly(assertions -> {
            assertions.assertThat(roadmapList).isNotNull();
            assertions.assertThat(roadmapList).hasSize(2);
        });
    }

    @DisplayName("다중 조건 검색이 정상적으로 동작한다. (직무 필터링, 서비스 필터링, 검색)")
    @Test
    void get_roadmap_list_by_roadmap_job_service_name() {
        Page<RoadmapRes> roadmapList = roadmapQueryRepository.getRoadmaplist("Backend Developer", "AWS EC2", "Learner Guide", PageRequest.of(0, 10));
        SoftAssertions.assertSoftly(assertions -> {
            assertions.assertThat(roadmapList).isNotNull();
            assertions.assertThat(roadmapList).hasSize(1);
            assertions.assertThat(roadmapList.getContent().get(0).roadmapId()).isEqualTo(dummyRoadmap2.getId());
            assertions.assertThat(roadmapList.getContent().get(0).service()).isEqualTo(dummyRoadmap2.getService());
        });
    }


}