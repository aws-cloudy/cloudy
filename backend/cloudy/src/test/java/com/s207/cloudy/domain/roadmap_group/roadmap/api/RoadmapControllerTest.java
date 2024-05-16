package com.s207.cloudy.domain.roadmap_group.roadmap.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.s207.cloudy.domain.learning.dto.LearningItem;
import com.s207.cloudy.domain.members.domain.Member;
import com.s207.cloudy.domain.roadmap_group.comment.application.RoadmapCommentService;
import com.s207.cloudy.domain.roadmap_group.comment.dto.RoadmapCommentDto;
import com.s207.cloudy.domain.roadmap_group.roadmap.application.RoadmapService;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapDetailsRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapListRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapRes;
import com.s207.cloudy.dummy.DummyMember;
import com.s207.cloudy.dummy.DummyRoadmap;
import com.s207.cloudy.dummy.DummyRoadmapComment;
import com.s207.cloudy.dummy.learning.DummyLearning;
import com.s207.cloudy.global.error.enums.ErrorCode;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = RoadmapController.class)
@AutoConfigureMockMvc(addFilters = false)
class RoadmapControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RoadmapController roadmapController;

    @MockBean
    RoadmapService mockRoadmapService;
    @MockBean
    RoadmapCommentService mockRoadmapCommentService;

    @Autowired
    ObjectMapper objectMapper;

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
    @DisplayName("페이지에 관한 정보를 파라미터로 입력 시 전체 로드맵 리스트를 정상적으로 조회해 반환하고, 200 OK를 반환한다.")
    void should_200_and_return_roadmaps_with_pagination_success_when_valid_parameter()
            throws Exception {

        List<RoadmapRes> dummyList = List.of(dummyRoadmapRes1, dummyRoadmapRes2);

        given(mockRoadmapService.findRoadmapList(any(), any(), any(), any(Pageable.class)))
                .willReturn(new RoadmapListRes(dummyList, dummyList.size()));

        mockMvc.perform(get("/api/v1/roadmaps?page=1&size=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roadmaps[0].roadmapId",
                        equalTo(dummyRoadmapRes1.roadmapId()), Integer.class))
                .andExpect(jsonPath("$.roadmaps[0].title",
                        equalTo(dummyRoadmapRes1.title()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].thumbnail",
                        equalTo(dummyRoadmapRes1.thumbnail()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].service",
                        equalTo(dummyRoadmapRes1.service()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].job",
                        equalTo(dummyRoadmapRes1.job()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].summary",
                        equalTo(dummyRoadmapRes1.summary()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].commentsCnt",
                        equalTo(dummyRoadmapRes1.commentsCnt()), Long.class))
                .andExpect(jsonPath("$.totalPageCnt",
                        equalTo((long) dummyList.size()), Long.class))
                .andDo(print());
    }

    @Test
    @DisplayName("전체 로드맵 리스트를 정상적으로 조회해 반환하고, 200 OK를 반환한다.")
    void should_200_and_return_roadmaps_success() throws Exception {

        List<RoadmapRes> dummyList = List.of(dummyRoadmapRes1, dummyRoadmapRes2);

        given(mockRoadmapService.findRoadmapList(any(), any(), any(), any()))
                .willReturn(new RoadmapListRes(dummyList, dummyList.size()));

        mockMvc.perform(get("/api/v1/roadmaps")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roadmaps[0].roadmapId",
                        equalTo(dummyRoadmapRes1.roadmapId()), Integer.class))
                .andExpect(jsonPath("$.roadmaps[0].title",
                        equalTo(dummyRoadmapRes1.title()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].thumbnail",
                        equalTo(dummyRoadmapRes1.thumbnail()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].service",
                        equalTo(dummyRoadmapRes1.service()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].job",
                        equalTo(dummyRoadmapRes1.job()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].summary",
                        equalTo(dummyRoadmapRes1.summary()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].commentsCnt",
                        equalTo(dummyRoadmapRes1.commentsCnt()), Long.class))
                .andExpect(jsonPath("$.totalPageCnt",
                        equalTo((long) dummyList.size()), Long.class))
                .andDo(print());
    }

    @Test
    @DisplayName("페이지 번호가 음수일 경우 에러코드와 함께 400 BAD REQUEST를 반환한다.")
    void should_400_and_return_roadmaps_failed_when_invalid_page_number() throws Exception {

        List<RoadmapRes> dummyList = List.of(dummyRoadmapRes1, dummyRoadmapRes2);

        int page = -1;

        given(mockRoadmapService.findRoadmapList(any(), any(), any(), any(Pageable.class)))
                .willReturn(new RoadmapListRes(dummyList, dummyList.size()));

        mockMvc.perform(get("/api/v1/roadmaps?page={pageSize}", page)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code",
                        equalTo(ErrorCode.INVALID_PAGINATION_PAGE.getCode()), String.class))
                .andExpect(jsonPath("$.message",
                        equalTo(ErrorCode.INVALID_PAGINATION_PAGE.getMessage()), String.class))
                .andDo(print());
    }

    @Test
    @DisplayName("페이지 사이즈가 음수일 경우 에러코드와 함께 400 BAD REQUEST를 반환한다.")
    void should_400_and_return_roadmaps_failed_when_invalid_page_size() throws Exception {

        List<RoadmapRes> dummyList = List.of(dummyRoadmapRes1, dummyRoadmapRes2);

        int pageSize = -1;

        given(mockRoadmapService.findRoadmapList(any(), any(), any(), any(Pageable.class)))
                .willReturn(new RoadmapListRes(dummyList, dummyList.size()));

        mockMvc.perform(get("/api/v1/roadmaps?size={pageSize}", pageSize)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code",
                        equalTo(ErrorCode.INVALID_PAGINATION_SIZE.getCode()), String.class))
                .andExpect(jsonPath("$.message",
                        equalTo(ErrorCode.INVALID_PAGINATION_SIZE.getMessage()), String.class))
                .andDo(print());
    }

    @Test
    @DisplayName("로드맵 상세를 정상적으로 조회해 반환하고, 200 OK를 반환한다.")
    void should_200_and_return_roadmap_success() throws Exception {


        List<LearningItem> courses =
                List.of(DummyLearning.getDummyLearningItem1(), DummyLearning.getDummyLearningItem2());
        given(mockRoadmapService.getRoadmapDetails(anyInt()))
                .willReturn(new RoadmapDetailsRes(dummyRoadmapRes1, courses));

        mockMvc.perform(get("/api/v1/roadmaps/{roadmapId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.detail.roadmapId",
                        equalTo(dummyRoadmapRes1.roadmapId()), Integer.class))
                .andExpect(jsonPath("$.detail.title",
                        equalTo(dummyRoadmapRes1.title()), String.class))
                .andExpect(jsonPath("$.detail.thumbnail",
                        equalTo(dummyRoadmapRes1.thumbnail()), String.class))
                .andExpect(jsonPath("$.detail.service",
                        equalTo(dummyRoadmapRes1.service()), String.class))
                .andExpect(jsonPath("$.detail.job",
                        equalTo(dummyRoadmapRes1.job()), String.class))
                .andExpect(jsonPath("$.detail.summary",
                        equalTo(dummyRoadmapRes1.summary()), String.class))
                .andExpect(jsonPath("$.detail.commentsCnt",
                        equalTo(dummyRoadmapRes1.commentsCnt()), Long.class))
                .andExpect(jsonPath("$.courses[0].learningId",
                        equalTo(courses.get(0).getLearningId()), Integer.class))
                .andDo(print());
    }

    @Test
    @DisplayName("로드맵 댓글을 정상적으로 조회해 반환하고, 200 OK를 반환한다.")
    void should_200_and_return_roadmap_comments_success() throws Exception {

        Member dummyMember = DummyMember.getDummyMember();
        List<RoadmapCommentDto> comments =
                List.of(DummyRoadmapComment.getDummyRoadmapComment(dummyRoadmap, dummyMember).toDto());

        given(mockRoadmapCommentService.getRoadmapCommentList(anyInt())).willReturn(comments);

        mockMvc.perform(get("/api/v1/roadmaps/{roadmapId}/comments", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.comments[0].commentId",
                        equalTo(comments.get(0).commentId()), Integer.class))
                .andExpect(jsonPath("$.comments[0].member.id",
                        equalTo(comments.get(0).member().id()), String.class))
                .andExpect(jsonPath("$.comments[0].member.name",
                        equalTo(comments.get(0).member().name()), String.class))
                .andExpect(jsonPath("$.comments[0].content",
                        equalTo(comments.get(0).content()), String.class))
                .andExpect(jsonPath("$.comments[0].regAt",
                        Matchers.startsWith(Integer.toString(comments.get(0).regAt().getYear()))
                        , String.class))
                .andDo(print());
    }
}
