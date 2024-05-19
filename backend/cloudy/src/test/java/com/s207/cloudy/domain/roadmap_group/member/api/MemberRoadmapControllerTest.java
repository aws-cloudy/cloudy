package com.s207.cloudy.domain.roadmap_group.member.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.s207.cloudy.domain.members.domain.Member;
import com.s207.cloudy.domain.roadmap_group.member.application.MemberRoadmapService;
import com.s207.cloudy.domain.roadmap_group.member.domain.MemberRoadmap;
import com.s207.cloudy.domain.roadmap_group.member.dto.BookmarkListRes;
import com.s207.cloudy.domain.roadmap_group.member.dto.BookmarkRes;
import com.s207.cloudy.domain.roadmap_group.member.dto.CreateRoadmapReq;
import com.s207.cloudy.domain.roadmap_group.roadmap.application.RoadmapService;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import com.s207.cloudy.dummy.DummyMember;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = MemberRoadmapController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class)
class MemberRoadmapControllerTest {
    static String BOOKMARK_URI = "/api/v1/bookmarks";


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    MemberRoadmapController memberRoadmapController;

    @MockBean
    MemberRoadmapService mockMemberRoadmapService;

    @MockBean
    RoadmapService mockRoadmapService;

    @Autowired
    ObjectMapper objectMapper;
    Member dummyMember;
    Roadmap dummyRoadmap;
    BookmarkRes dummyBookmarkRes1;
    BookmarkRes dummyBookmarkRes2;

    MemberRoadmap dummyMemberRoadmap;

    @BeforeEach
    void setUp() {
        dummyMember = DummyMember.getDummyMember();
        dummyRoadmap = DummyRoadmap.getDummyRoadmap();
        dummyMemberRoadmap = DummyRoadmap.getDummyMemberRoadmap(dummyMember, dummyRoadmap);
        dummyBookmarkRes1 = DummyRoadmap.getBookmarkRes(dummyMemberRoadmap);
        dummyBookmarkRes2 = DummyRoadmap.getBookmarkRes(dummyMemberRoadmap);
    }


    @Test
    @DisplayName("회원이 북마크한 로드맵 리스트를 정상적으로 조회해 반환하고, 200 OK를 반환한다.")
    void should_200_and_return_member_bookmarks_success_when_valid_request() throws Exception {

        List<BookmarkRes> dummyList = List.of(dummyBookmarkRes1, dummyBookmarkRes2);

        given(mockMemberRoadmapService.findRoadmapListByMember(any(Member.class)))
                .willReturn(new BookmarkListRes(dummyList, dummyList.size()));

        mockMvc.perform(get(BOOKMARK_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roadmaps[0].bookmarkId",
                        equalTo(dummyBookmarkRes1.bookmarkId()), Integer.class))
                .andExpect(jsonPath("$.roadmaps[0].roadmapId",
                        equalTo(dummyBookmarkRes1.roadmapId()), Integer.class))
                .andExpect(jsonPath("$.roadmaps[0].title",
                        equalTo(dummyBookmarkRes1.title()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].thumbnail",
                        equalTo(dummyBookmarkRes1.thumbnail()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].service",
                        equalTo(dummyBookmarkRes1.service()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].job",
                        equalTo(dummyBookmarkRes1.job()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].summary",
                        equalTo(dummyBookmarkRes1.summary()), String.class))
                .andExpect(jsonPath("$.roadmaps[0].commentsCnt",
                        equalTo(dummyBookmarkRes1.commentsCnt()), Long.class))
                .andExpect(jsonPath("$.totalPageCnt",
                        equalTo((long) dummyList.size()), Long.class))
                .andDo(print());
    }

    @Test
    @DisplayName("로드맵 번호가 정상적으로 들어왔을 경우 북마크 생성에 성공하고, 201 CREATED를 반환한다.")
    void should_201_and_create_bookmark_success_when_valid_request_body() throws Exception {

        CreateRoadmapReq createRoadmapReq = new CreateRoadmapReq(dummyRoadmap.getId());

        given(mockMemberRoadmapService.createRoadmapBookmark(any(Member.class), any(CreateRoadmapReq.class)))
                .willReturn(dummyMemberRoadmap);

        mockMvc.perform(post(BOOKMARK_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRoadmapReq)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("로드맵 번호가 null일 경우 북마크 생성에 실패하고, 400 BAD REQUEST를 반환한다.")
    void should_400_and_create_bookmark_failed_when_invalid_request_body() throws Exception {

        given(mockMemberRoadmapService.createRoadmapBookmark(any(Member.class), any(CreateRoadmapReq.class)))
                .willReturn(dummyMemberRoadmap);

        mockMvc.perform(post(BOOKMARK_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString( new CreateRoadmapReq(null))))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("로드맵 북마크 번호가 정상적으로 들어왔을 경우 북마크 삭제에 성공하고, 204 NO CONTENT를 반환한다.")
    void should_204_and_delete_bookmark_success_when_valid_path_variable() throws Exception {

        CreateRoadmapReq createRoadmapReq = new CreateRoadmapReq(dummyRoadmap.getId());

        mockMvc.perform(delete(BOOKMARK_URI + "/{bookmarkId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRoadmapReq)))
                .andExpect(status().isNoContent())
                .andDo(print());
    }


}