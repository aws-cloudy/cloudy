package com.s207.cloudy.domain.roadmap_group.member.application;

import com.s207.cloudy.domain.members.entity.Member;
import com.s207.cloudy.domain.roadmap_group.member.dao.MemberRoadmapQueryRepository;
import com.s207.cloudy.domain.roadmap_group.member.dao.MemberRoadmapRepository;
import com.s207.cloudy.domain.roadmap_group.member.domain.MemberRoadmap;
import com.s207.cloudy.domain.roadmap_group.member.dto.CreateRoadmapReq;
import com.s207.cloudy.domain.roadmap_group.roadmap.application.RoadmapService;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapListRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapRes;
import com.s207.cloudy.dummy.DummyMember;
import com.s207.cloudy.dummy.DummyRoadmap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@SpringJUnitConfig(MemberRoadmapServiceImpl.class)
class MemberRoadmapServiceImplTest {
    @Autowired
    MemberRoadmapService memberRoadmapService;

    @MockBean
    RoadmapService mockRoadmapService;

    @MockBean
    MemberRoadmapQueryRepository mockMemberRoadmapQueryRepository;

    @MockBean
    MemberRoadmapRepository mockMemberRoadmapRepository;


    Roadmap dummyRoadmap;
    RoadmapRes dummyRoadmapRes1;
    RoadmapRes dummyRoadmapRes2;
    Member dummyMember;
    MemberRoadmap dummyMemberRoadmap;

    @BeforeEach
    void setUp() {
        dummyRoadmap = DummyRoadmap.getDummyRoadmap();
        dummyRoadmapRes1 = DummyRoadmap.getDummyRoadmapRes(dummyRoadmap);
        dummyRoadmapRes2 = DummyRoadmap.getDummyRoadmapRes(dummyRoadmap);
        dummyMember = DummyMember.getDummyMember();
        dummyMemberRoadmap = DummyRoadmap.getDummyMemberRoadmap(dummyMember, dummyRoadmap);
    }

    @Test
    @DisplayName("회원이 북마크한 로드맵의 아이디 리스트를 정상적으로 조회한다.")
    void return_roadmap_list_id_by_member_success() {

        List<Integer> roadmapIdList = List.of(dummyRoadmapRes1.getRoadmapId(), dummyRoadmapRes2.getRoadmapId());

        // given
        given(mockMemberRoadmapQueryRepository.getRoadmapIdListByMemberId(anyString()))
                .willReturn(roadmapIdList);

        // when
        List<Integer> actualRoadmaps = memberRoadmapService.findRoadmapIdList(dummyMember.getUsername());

        // then
        Assertions.assertThat(actualRoadmaps).isNotNull();
        Assertions.assertThat(actualRoadmaps).hasSize(roadmapIdList.size());
    }

    @Test
    @DisplayName("회원이 북마크한 로드맵 리스트를 정상적으로 조회한다.")
    void return_roadmap_list_by_member_success() {

        List<Integer> roadmapIdList = List.of(dummyRoadmapRes1.getRoadmapId(), dummyRoadmapRes2.getRoadmapId());
        List<RoadmapRes> dummyList = List.of(dummyRoadmapRes1, dummyRoadmapRes2);

        // given
        given(mockMemberRoadmapQueryRepository.getRoadmapIdListByMemberId(anyString()))
                .willReturn(roadmapIdList);
        given(mockRoadmapService.findMemberRoadmapList(anyList()))
                .willReturn(new RoadmapListRes(dummyList, dummyList.size()));

        // when
        RoadmapListRes actualRoadmaps = memberRoadmapService.findRoadmapListByMember(dummyMember);

        // then
        Assertions.assertThat(actualRoadmaps).isNotNull();
        Assertions.assertThat(actualRoadmaps.getRoadmaps()).hasSize(roadmapIdList.size());
    }

    @Test
    @DisplayName("회원이 북마크한 로드맵을 정상적으로 저장한다.")
    void create_bookmark_success() {

        CreateRoadmapReq createRoadmapReq = new CreateRoadmapReq(dummyRoadmap.getId());

        // given
        given(mockRoadmapService.findRoadmapEntity(anyInt()))
                .willReturn(dummyRoadmap);
        given(mockMemberRoadmapRepository.save(any(MemberRoadmap.class)))
                .willReturn(dummyMemberRoadmap);

        // when
        MemberRoadmap actualRoadmapBookmark = memberRoadmapService.createRoadmapBookmark(dummyMember, createRoadmapReq);

        // then
        Assertions.assertThat(actualRoadmapBookmark).isNotNull();
        Assertions.assertThat(actualRoadmapBookmark.getMemberId()).isEqualTo(dummyMember.getId());
        Assertions.assertThat(actualRoadmapBookmark.getRoadmap()).isEqualTo(dummyRoadmap);
    }


}