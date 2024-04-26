package com.s207.cloudy.domain.learning.application;

import com.s207.cloudy.domain.learning.dto.LearningItem;
import com.s207.cloudy.domain.learning.dto.LearningListRes;
import com.s207.cloudy.domain.learning.dto.LearningSearchReq;
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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringJUnitConfig(LearningServiceImpl.class)
public class LearningServiceImplTest {

    @Autowired
    LearningService learningService;

    @MockBean
    LearningRepository learningRepository;

    @MockBean
    JobRepository jobRepository;

    LearningItem dummyItem1;
    LearningItem dummyItem2;

    @BeforeEach
    void setUp() {
        dummyItem1 = DummyLearning.getDummyLearningItem1();
        dummyItem2 = DummyLearning.getDummyLearningItem2();
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



}
