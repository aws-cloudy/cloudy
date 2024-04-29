package com.s207.cloudy.domain.roadmap_group.member.api;

import com.s207.cloudy.domain.members.entity.Member;
import com.s207.cloudy.domain.roadmap_group.member.application.MemberRoadmapService;
import com.s207.cloudy.domain.roadmap_group.roadmap.application.RoadmapService;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapListRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapRes;
import com.s207.cloudy.dummy.DummyRoadmap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = MemberRoadmapController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class)
class MemberRoadmapControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    MemberRoadmapController memberRoadmapController;

    @MockBean
    MemberRoadmapService mockMemberRoadmapService;

    @MockBean
    RoadmapService mockRoadmapService;

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
    @DisplayName("회원이 북마크한 로드맵 리스트를 정상적으로 조회해 반환하고, 200 OK를 반환한다.")
    void should_200_when_get_member_bookmarklist() throws Exception {

        List<RoadmapRes> dummyList = List.of(dummyRoadmapRes1, dummyRoadmapRes2);

        given(mockMemberRoadmapService.getRoadmapList(any(Member.class)))
                .willReturn(new RoadmapListRes(dummyList, dummyList.size()));

        mockMvc.perform(get("/api/v1/my/roadmaps")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roadmaps[0].roadmapId",
                        equalTo(dummyRoadmapRes1.getRoadmapId()), Integer.class))
                .andExpect(jsonPath("$.roadmaps[0].title",
                        equalTo(dummyRoadmapRes1.getTitle()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].thumbnail",
                        equalTo(dummyRoadmapRes1.getThumbnail()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].service",
                        equalTo(dummyRoadmapRes1.getService()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].job",
                        equalTo(dummyRoadmapRes1.getJob()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].summary",
                        equalTo(dummyRoadmapRes1.getSummary()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].commentsCnt",
                        equalTo(dummyRoadmapRes1.getCommentsCnt()), Long.class))
                .andExpect(jsonPath("$.totalPageCnt",
                        equalTo((long) dummyList.size()), Long.class))
                .andDo(print());
    }


}