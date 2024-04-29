package com.s207.cloudy.domain.roadmap_group.roadmap.application;

import com.s207.cloudy.domain.roadmap_group.roadmap.dao.RoadmapQueryRepository;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapListRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapRes;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringJUnitConfig(RoadmapServiceImpl.class)
class RoadmapServiceImplTest {


    @Autowired
    RoadmapService roadmapService;

    @MockBean
    RoadmapQueryRepository mockRoadmapRepository;

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
        given(mockRoadmapRepository.getRoadmaplist(anyString(), anyString(), anyString(), any()))
                .willReturn(new PageImpl<>(dummyList, PageRequest.of(0, 10), dummyList.size()));

        // when
        RoadmapListRes actualRoadmaps = roadmapService.findRoadmapList(
                "backend developer", "AWS EC2", "Learning guide",
                PageRequest.of(0, 10));

        // then
        Assertions.assertThat(actualRoadmaps).isNotNull();
        Assertions.assertThat(actualRoadmaps.getRoadmaps()).hasSize(dummyList.size());
    }

}