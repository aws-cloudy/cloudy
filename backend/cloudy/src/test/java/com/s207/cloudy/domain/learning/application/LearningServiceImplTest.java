package com.s207.cloudy.domain.learning.application;

import com.s207.cloudy.domain.learning.dao.JobRepository;
import com.s207.cloudy.domain.learning.dao.LearningRepository;
import com.s207.cloudy.domain.learning.domain.Learning;
import com.s207.cloudy.domain.learning.dto.LearningItem;
import com.s207.cloudy.domain.learning.dto.LearningListRes;
import com.s207.cloudy.domain.learning.dto.LearningSearchReq;
import com.s207.cloudy.domain.learning.exception.LearningException;
import com.s207.cloudy.dummy.learning.DummyLearning;
import com.s207.cloudy.global.error.enums.ErrorCode;
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
import static org.mockito.Mockito.*;

@SpringJUnitConfig(LearningServiceImpl.class)
class LearningServiceImplTest {

    @Autowired
    LearningService learningService;

    @MockBean
    LearningRepository learningRepository;

    @MockBean
    ConvertUtil convertUtil;

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
    @DisplayName("직무명만을 포함해 조회 요청 시 직무명 변환 메서드를 실행하고, 다른 변환 메서드는 실행이 되지 않는다.")
    void getLearningsWithType() {
        LearningSearchReq req = new LearningSearchReq();
        req.setPageSize(2);
        req.setJobName(new String[]{""});

        // given
        given(learningRepository.findLearnings(any())).willReturn(List.of());
        given(convertUtil.covertJobName(any())).willReturn(new String[]{""});

        // when
        learningService.getLearnings(req);

        // then
        verify(convertUtil, times(1)).covertJobName(req.getJobName());
        verify(convertUtil, never()).covertServiceName(req.getServiceName());
        verify(convertUtil, never()).covertDifficulty(req.getDifficulty());
        verify(convertUtil, never()).covertType(req.getType());
    }

    @Test
    @DisplayName("난이도, 강의분류, 서비스명, 직무명 포함해 조회 요청 시 각각의 필드에 대한 변환 메서드를 실행한다.")
    void getLearningsWithAllParameter() {
        LearningSearchReq req = new LearningSearchReq();
        req.setPageSize(2);
        req.setDifficulty(new String[]{""});
        req.setJobName(new String[]{""});
        req.setType(new String[]{""});
        req.setServiceName(new String[]{""});

        // given
        given(learningRepository.findLearnings(any())).willReturn(List.of());
        given(convertUtil.covertJobName(any())).willReturn(new String[]{""});
        given(convertUtil.covertType(any())).willReturn(new String[]{""});
        given(convertUtil.covertServiceName(any())).willReturn(new String[]{""});
        given(convertUtil.covertDifficulty(any())).willReturn(new String[]{""});

        // when
        learningService.getLearnings(req);

        // then
        verify(convertUtil, times(1)).covertJobName(req.getJobName());
        verify(convertUtil, times(1)).covertServiceName(req.getServiceName());
        verify(convertUtil, times(1)).covertDifficulty(req.getDifficulty());
        verify(convertUtil, times(1)).covertType(req.getType());
    }

    @Test
    @DisplayName("로그인 한 회원이 존재하지 않는 직무로 학습 조회 시 예외를 터뜨린다.")
    void getLearningsFailedWhenNonExistJob() {

        given(jobRepository.existsJobId(anyInt())).willReturn(Boolean.FALSE);

        // when
        // then
        Assertions.assertThatThrownBy(()->learningService.getLearningsByJob(1,1))
                .isInstanceOf(LearningException.class)
                .hasMessageContaining(ErrorCode.INVALID_JOB_ID.getMessage());
    }

    @Test
    @DisplayName("검색어 수정전의 전체 학습 목록을 정상적으로 조회한다.")
    void getLearnings() throws Exception {

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
        Assertions.assertThat(list.getIsModified()).isFalse();
        Assertions.assertThat(list.getLearningList()).hasSize(dummyList.size());
    }

    @Test
    @DisplayName("로그인 시, 지정한 개수만큼 해당 직무번호와 일치하는 전체 학습 목록을 랜덤하게 조회한다.")
    void getListByJobWhenLoginSuccess() throws Exception {

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
    void getListByJobWhenLoginFail() throws Exception {

        given(jobRepository.existsJobId(anyInt()))
                .willThrow(new LearningException(ErrorCode.INVALID_JOB_ID));

        // given
        int jobId = 1000;
        int count = 2;

        // when & then
        LearningException e = assertThrows(LearningException.class, () -> {
            learningService.getLearningsByJob(jobId, count);
        });

        Assertions.assertThat(e.getCode()).isEqualTo("SE002");
        Assertions.assertThat(e.getMessage()).isEqualTo("존재하지 않는 직무 아이디입니다");
        Assertions.assertThat(e.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("로그아웃 시, 지정한 개수만큼 전체 학습 목록을 랜덤하게 조회한다.")
    void getListByJobWhenLogoutSuccess() throws Exception {

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

    @Test
    @DisplayName("로드맵id로 로드맵에 포함된 학습 리스트를 조회한다.")
    void getCourseListByRoadmapIdSuccess() {
        List<Learning> courses =
                List.of(DummyLearning.getDummyLearning1(), DummyLearning.getDummyLearning2());

        given(learningRepository.findByRoadmapId(anyInt()))
                .willReturn(courses);

        List<LearningItem> actualCourses = learningService.getCoursesWithRoadmapId(1);

        Assertions.assertThat(actualCourses).hasSize(courses.size());
    }

}
