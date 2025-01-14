package com.s207.cloudy.domain.roadmap_group.member.dao;

import com.s207.cloudy.TestQueryDslConfig;
import com.s207.cloudy.domain.members.domain.Member;
import com.s207.cloudy.domain.roadmap_group.member.domain.MemberRoadmap;
import com.s207.cloudy.domain.roadmap_group.member.dto.BookmarkRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import com.s207.cloudy.dummy.DummyMember;
import com.s207.cloudy.dummy.DummyRoadmap;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;

import java.util.List;

@DataJpaTest
@Import(TestQueryDslConfig.class)
class MemberRoadmapQueryRepositoryTest {

    @Autowired
    MemberRoadmapQueryRepository memberRoadmapQueryRepository;

    @Autowired
    TestEntityManager entityManager;

    Member dummyMember;

    Roadmap dummyRoadmap1;
    Roadmap dummyRoadmap2;

    @BeforeEach
    void setUp() {

        dummyMember = DummyMember.getDummyMember();

        dummyRoadmap1 = DummyRoadmap.getDummyRoadmap();
        dummyRoadmap2 = DummyRoadmap.getDummyRoadmap();
        entityManager.persist(dummyRoadmap1);
        entityManager.persist(dummyRoadmap2);

        String userId = dummyMember.getUsername();
        MemberRoadmap memberRoadmap1 = new MemberRoadmap(userId, dummyRoadmap1);
        MemberRoadmap memberRoadmap2 = new MemberRoadmap(userId, dummyRoadmap2);

        entityManager.persist(memberRoadmap1);
        entityManager.persist(memberRoadmap2);

        entityManager.flush();
        entityManager.clear();
    }

    @DisplayName("회원별 전체 로드맵 리스트를 정상적으로 조회한다.")
    @Test
    void return_roadmap_list_by_member_id_success() {
        Page<BookmarkRes> roadmapPageList = memberRoadmapQueryRepository.getRoadmapListByMemberId(dummyMember.getUsername());
        List<BookmarkRes> roadmapList = roadmapPageList.getContent();
        SoftAssertions.assertSoftly(assertions -> {
            assertions.assertThat(roadmapList).isNotNull();
            assertions.assertThat(roadmapList.get(0).roadmapId()).isEqualTo(dummyRoadmap1.getId());
            assertions.assertThat(roadmapList.get(1).roadmapId()).isEqualTo(dummyRoadmap2.getId());
        });
    }
}