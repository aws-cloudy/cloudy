package com.s207.cloudy.domain.roadmap_group.roadmap.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.s207.cloudy.domain.learning.application.LearningService;
import com.s207.cloudy.domain.learning.dto.LearningItem;
import com.s207.cloudy.domain.members.application.MemberService;
import com.s207.cloudy.domain.roadmap_group.roadmap.dao.RoadmapQueryRepository;
import com.s207.cloudy.domain.roadmap_group.roadmap.dao.RoadmapRepository;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapDetailsRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapListRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.exception.RoadmapNotFoundException;
import com.s207.cloudy.dummy.DummyRoadmap;
import com.s207.cloudy.dummy.learning.DummyLearning;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(RoadmapServiceImpl.class)
class RoadmapServiceImplTest {

    @Autowired
    RoadmapService roadmapService;

    @MockBean
    RoadmapQueryRepository mockRoadmapQueryRepository;

    @MockBean
    RoadmapRepository mockRoadmapRepository;

    @MockBean
    MemberService mockMemberService;

    @MockBean
    LearningService mockLearningService;

    Roadmap dummyRoadmap;
    RoadmapRes dummyRoadmapRes1;
    RoadmapRes dummyRoadmapRes2;

    @BeforeEach
    void setUp() {
        dummyRoadmap = DummyRoadmap.getDummyRoadmap();
        dummyRoadmapRes1 = DummyRoadmap.getDummyRoadmapRes(dummyRoadmap);
        dummyRoadmapRes2 = DummyRoadmap.getDummyRoadmapRes(dummyRoadmap);
    }

    @Test
    @DisplayName("전체 로드맵 리스트를 정상적으로 조회한다.")
    void return_roadmap_list_success() {

        List<RoadmapRes> dummyList = List.of(dummyRoadmapRes1, dummyRoadmapRes2);

        // given
        given(
            mockRoadmapQueryRepository.getRoadmaplist(anyString(), anyString(), anyString(), any()))
            .willReturn(new PageImpl<>(dummyList, PageRequest.of(0, 10), dummyList.size()));

        // when
        RoadmapListRes actualRoadmaps = roadmapService.findRoadmapList(
            "backend developer", "AWS EC2", "Learning guide",
            PageRequest.of(0, 10));

        // then
        Assertions.assertThat(actualRoadmaps).isNotNull();
        Assertions.assertThat(actualRoadmaps.getRoadmaps()).hasSize(dummyList.size());
    }

    @Test
    @DisplayName("로드맵 엔터티를 정상적으로 조회한다.")
    void return_roadmap_entity_success() {

        // given
        given(mockRoadmapRepository.findById(anyInt()))
            .willReturn(Optional.of(dummyRoadmap));

        // when
        Roadmap actualRoadmap = roadmapService.findRoadmapEntity(1);

        // then
        Assertions.assertThat(actualRoadmap).isNotNull();
        Assertions.assertThat(actualRoadmap.getId()).isEqualTo(dummyRoadmap.getId());
    }

    @Test
    @DisplayName("로드맵 상세를 정상적으로 조회한다.")
    void return_roadmap_details_success() {
        List<LearningItem> learningItems = List.of(DummyLearning.getDummyLearningItem1(),
            DummyLearning.getDummyLearningItem2());

        // given
        given(mockRoadmapRepository.findById(anyInt()))
            .willReturn(Optional.of(dummyRoadmap));
        given(mockLearningService.getCoursesWithRoadmapId(anyInt()))
            .willReturn(learningItems);

        // when
        RoadmapDetailsRes actualRoadmapDetail = roadmapService.getRoadmapDetails(1);

        // then
        Assertions.assertThat(actualRoadmapDetail).isNotNull();
        Assertions.assertThat(actualRoadmapDetail.detail().getRoadmapId())
            .isEqualTo(dummyRoadmap.getId());
        Assertions.assertThat(actualRoadmapDetail.courses().get(0).getLearningId())
            .isEqualTo(learningItems.get(0).getLearningId());
    }

    @Test
    @DisplayName("존재하지 않는 로드맵 조회 요청 시 예외를 터뜨린다.")
    void should_404_error_when_request_invalid_roadmap_details() {

        // given
        given(mockRoadmapRepository.findById(anyInt()))
            .willReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> roadmapService.getRoadmapDetails(1))
            .isInstanceOf(RoadmapNotFoundException.class)
            .hasMessage(RoadmapNotFoundException.MESSAGE);
    }

}