package com.s207.cloudy.domain.roadmap_group.comment.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.s207.cloudy.domain.members.application.MemberService;
import com.s207.cloudy.domain.members.domain.Member;
import com.s207.cloudy.domain.roadmap_group.comment.dao.RoadmapCommentRepository;
import com.s207.cloudy.domain.roadmap_group.comment.domain.RoadmapComment;
import com.s207.cloudy.domain.roadmap_group.comment.dto.RoadmapCommentDto;
import com.s207.cloudy.domain.roadmap_group.comment.dto.RoadmapCommentPostReq;
import com.s207.cloudy.domain.roadmap_group.roadmap.application.RoadmapService;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import com.s207.cloudy.dummy.DummyMember;
import com.s207.cloudy.dummy.DummyRoadmap;
import com.s207.cloudy.dummy.DummyRoadmapComment;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author 이하늬
 * @since 1.0
 */
@SpringJUnitConfig(RoadmapCommentServiceImpl.class)
class RoadmapCommentServiceImplTest {
    @Autowired
    RoadmapCommentService roadmapCommentService;

    @MockBean
    RoadmapCommentRepository mockRoadmapCommentRepository;

    @MockBean
    RoadmapService mockRoadmapService;

    @MockBean
    MemberService mockMemberService;

    Roadmap dummyRoadmap;
    Member dummyMember;

    @BeforeEach
    void setUp() {
        dummyRoadmap = DummyRoadmap.getDummyRoadmap();
        dummyMember = DummyMember.getDummyMember();
    }

    @Test
    @DisplayName("로드맵 댓글 리스트를 정상적으로 조회한다.")
    void return_roadmap_comments_list_by_roadmapId_success() {

        List<RoadmapComment> dummyComments =
            List.of(DummyRoadmapComment.getDummyRoadmapComment(dummyRoadmap, dummyMember));

        // given
        given(mockRoadmapCommentRepository.findByRoadmapId(anyInt())).willReturn(dummyComments);

        // when
        List<RoadmapCommentDto> actualComments = roadmapCommentService.getRoadmapCommentList(
            dummyRoadmap.getId());

        // then
        Assertions.assertThat(actualComments).isNotEmpty();
        Assertions.assertThat(actualComments).hasSize(dummyComments.size());
    }

    @Test
    @DisplayName("로드맵 댓글을 정상적으로 저장한다.")
    void create_roadmap_comments_success() {

        RoadmapCommentPostReq roadmapCommentPostReq =
            new RoadmapCommentPostReq(dummyRoadmap.getId(), dummyMember.getId(), "content");

        RoadmapComment comment = RoadmapComment.builder()
            .roadmap(dummyRoadmap)
            .member(dummyMember)
            .content(roadmapCommentPostReq.content())
            .build();

        // given
        given(mockRoadmapService.findRoadmapEntity(anyInt()))
            .willReturn(dummyRoadmap);
        given(mockMemberService.findMemberEntity(anyString()))
            .willReturn(dummyMember);
        given(mockRoadmapCommentRepository.save(any(RoadmapComment.class)))
            .willReturn(comment);

        // when
        Integer commentId =
            roadmapCommentService.postRoadmapComment(roadmapCommentPostReq, dummyMember.getId());

        // then
        Assertions.assertThat(commentId).isNotNull();
    }


}