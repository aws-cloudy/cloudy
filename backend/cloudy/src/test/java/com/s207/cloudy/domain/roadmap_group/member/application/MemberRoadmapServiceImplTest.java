package com.s207.cloudy.domain.roadmap_group.member.application;

import com.s207.cloudy.domain.members.domain.Member;
import com.s207.cloudy.domain.roadmap_group.member.dao.MemberRoadmapQueryRepository;
import com.s207.cloudy.domain.roadmap_group.member.dao.MemberRoadmapRepository;
import com.s207.cloudy.domain.roadmap_group.member.domain.MemberRoadmap;
import com.s207.cloudy.domain.roadmap_group.member.dto.BookmarkListRes;
import com.s207.cloudy.domain.roadmap_group.member.dto.BookmarkRes;
import com.s207.cloudy.domain.roadmap_group.member.dto.CreateRoadmapReq;
import com.s207.cloudy.domain.roadmap_group.roadmap.application.RoadmapService;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import com.s207.cloudy.dummy.DummyMember;
import com.s207.cloudy.dummy.DummyRoadmap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

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
    MemberRoadmap dummyMemberRoadmap;
    BookmarkRes dummyBookmarkRes1;
    BookmarkRes dummyBookmarkRes2;
    Member dummyMember;

    @BeforeEach
    void setUp() {
        dummyRoadmap = DummyRoadmap.getDummyRoadmap();
        dummyMember = DummyMember.getDummyMember();
        dummyMemberRoadmap = DummyRoadmap.getDummyMemberRoadmap(dummyMember, dummyRoadmap);
        dummyBookmarkRes1 = DummyRoadmap.getBookmarkRes(dummyMemberRoadmap);
        dummyBookmarkRes2 = DummyRoadmap.getBookmarkRes(dummyMemberRoadmap);
    }

    @Test
    @DisplayName("회원이 북마크한 로드맵 리스트를 정상적으로 조회한다.")
    void return_roadmap_list_by_member_success() {

        List<BookmarkRes> dummyList = List.of(dummyBookmarkRes1, dummyBookmarkRes2);
        // given
        given(mockMemberRoadmapQueryRepository.getRoadmapListByMemberId(anyString()))
                .willReturn(new PageImpl<>(dummyList, PageRequest.of(0, 10), dummyList.size()));

        // when
        BookmarkListRes actualRoadmaps = memberRoadmapService.findRoadmapListByMember(dummyMember);

        // then
        Assertions.assertThat(actualRoadmaps).isNotNull();
        Assertions.assertThat(actualRoadmaps.getRoadmaps()).hasSize(dummyList.size());
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

    @Test
    @DisplayName("북마크를 정상적으로 삭제한다.")
    void delete_roadmap_bookmark_success() {

        // given
        given(mockMemberRoadmapRepository.findById(anyInt()))
                .willReturn(Optional.of(dummyMemberRoadmap));

        // when
        memberRoadmapService.deleteById(1);

        // then
        Assertions.assertThatNoException();
    }

    @Test
    @DisplayName("회원-로드맵 엔터티를 정상적으로 조회한다.")
    void return_member_roadmap_entity_success() {

        // given
        given(mockMemberRoadmapRepository.findById(anyInt()))
                .willReturn(Optional.of(dummyMemberRoadmap));

        // when
        MemberRoadmap actualMemberRoadmap = memberRoadmapService.findMemberRoadmapEntity(1);

        // then
        Assertions.assertThat(actualMemberRoadmap).isNotNull();
        Assertions.assertThat(actualMemberRoadmap.getId()).isEqualTo(dummyMemberRoadmap.getId());
    }
}