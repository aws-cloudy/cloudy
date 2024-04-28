package com.s207.cloudy.domain.learning.application;

import com.s207.cloudy.domain.learning.dto.LearningItem;
import com.s207.cloudy.domain.learning.dto.LearningListRes;
import com.s207.cloudy.domain.learning.dto.LearningSearchReq;
import com.s207.cloudy.domain.learning.exception.LearningErrorCode;
import com.s207.cloudy.domain.learning.exception.LearningException;
import com.s207.cloudy.domain.learning.repository.JobRepository;
import com.s207.cloudy.domain.learning.repository.LearningRepository;
import com.s207.cloudy.domain.learning.service.LearningService;
import com.s207.cloudy.domain.learning.service.LearningServiceImpl;
import com.s207.cloudy.dummy.learning.DummyLearning;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@SpringJUnitConfig(LearningServiceImpl.class)
public class LearningServiceImplTest {

    @Autowired
    LearningService learningService;

    @MockBean
    LearningRepository learningRepository;

    @MockBean
    JobRepository jobRepository;

    LearningItem dummyItem1, dummyItem2, dummyItem3, dummyItem4;

    @BeforeEach
    void setUp() {
        dummyItem1 = DummyLearning.getDummyLearningItem1();
        dummyItem2 = DummyLearning.getDummyLearningItem2();
        dummyItem3 = DummyLearning.getDummyLearningItem2();
        dummyItem4 = DummyLearning.getDummyLearningItem2();
    }

    @Test
    @DisplayName("검색어 수정전의 전체 학습 목록을 정상적으로 조회한다.")
    void getLearnings() throws Exception{

        List<LearningItem> dummyList = List.of(dummyItem1, dummyItem2);
        given(learningRepository.findLearnings(any()))
                .willReturn(dummyList);

        // given
        LearningSearchReq req = new LearningSearchReq();
        req.setPageSize(2);
        req.setDifficulty(new String[]{"1"});
        req.setType(new String[]{"Digital_Course"});

        // when
        LearningListRes list = learningService.getLearnings(req);

        // then
        Assertions.assertThat(list.getLearningList()).isNotNull();
        Assertions.assertThat(list.getIsModified()).isEqualTo(false);
        Assertions.assertThat(list.getLearningList()).hasSize(dummyList.size());
    }

    @Test
    @DisplayName("로그인 시, 지정한 개수만큼 해당 직무번호와 일치하는 전체 학습 목록을 랜덤하게 조회한다.")
    void getListByJobWhenLoginSuccess() throws Exception{

        given(jobRepository.existsJobId(anyInt()))
                .willReturn(true);

        List<LearningItem> dummyList = List.of(dummyItem3, dummyItem4);
        given(learningRepository.findLearningsByJob(anyInt(), anyInt()))
                .willReturn(dummyList);

        // given
        int jobId = 3;
        int count = 2;

        // when
        LearningListRes list = learningService.getLearningsByJob(jobId, count);

        // then
        Assertions.assertThat(list.getLearningList()).isNotNull();
        Assertions.assertThat(list.getLearningList()).hasSize(dummyList.size());
    }

    @Test
    @DisplayName("로그인 시, 직무가 존재하지 않으면 LearningException 예외가 발생한다.")
    void getListByJobWhenLoginFail() throws Exception{

        given(jobRepository.existsJobId(anyInt()))
                .willThrow(new LearningException(LearningErrorCode.INVALID_JOB_ID));

        // given
        int jobId = 1000;
        int count = 2;

        // when & then
        LearningException e = assertThrows(LearningException.class, () -> {
            learningService.getLearningsByJob(jobId, count);
        });
        Assertions.assertThat(e.getError().get("code").equals("SE001"));
        Assertions.assertThat(e.getError().get("message").equals("존재하지 않는 직무 아이디입니다"));
        Assertions.assertThat(e.getStatus().equals(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    @DisplayName("로그아웃 시, 지정한 개수만큼 전체 학습 목록을 랜덤하게 조회한다.")
    void getListByJobWhenLogoutSuccess() throws Exception{

        List<LearningItem> dummyList = List.of(dummyItem3, dummyItem4);
        given(learningRepository.findLearningsByJob(anyInt()))
                .willReturn(dummyList);

        // given
        int count = 2;

        // when
        LearningListRes list = learningService.getLearningsByJob(count);

        // then
        Assertions.assertThat(list.getLearningList()).isNotNull();
        Assertions.assertThat(list.getLearningList()).hasSize(dummyList.size());
    }

}
