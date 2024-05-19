package com.s207.cloudy.domain.roadmap_group.comment.api;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.s207.cloudy.domain.roadmap_group.comment.application.RoadmapCommentService;
import com.s207.cloudy.domain.roadmap_group.comment.dto.RoadmapCommentPostReq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author 이하늬
 * @since 1.0
 */
@WebMvcTest(controllers = CommentController.class,
    excludeAutoConfiguration = SecurityAutoConfiguration.class)
class CommentControllerTest {

    static String COMMENT_URI = "/api/v1/comments";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CommentController commentController;

    @MockBean
    RoadmapCommentService mockRoadmapCommentService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("로드맵 댓글 요청 바디가 정상적으로 들어왔을 경우 로드맵 댓글 생성에 성공하고, 200 OK를 반환한다.")
    void should_200_and_create_comments_success_when_valid_request_body() throws Exception {

        int commentId = 0;

        RoadmapCommentPostReq roadmapCommentPostReq =
            new RoadmapCommentPostReq(1, "id", "content");

        given(mockRoadmapCommentService.postRoadmapComment(any(RoadmapCommentPostReq.class),
            anyString())).willReturn(commentId);

        mockMvc.perform(post(COMMENT_URI+"/roadmaps")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roadmapCommentPostReq)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", equalTo(commentId), Integer.class))
            .andDo(print());
    }
}