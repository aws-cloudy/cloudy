package com.s207.cloudy.domain.learning.application;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(ConvertUtil.class)
class ConvertUtilTest {
    @Autowired
    ConvertUtil convertUtil;

    @DisplayName("난이도 배열을 지정한 Enum 내부의 값을 가지도록 정상적으로 변환한다.")
    @Test
    void convert_difficulty_success() {
        String[] difficulty = new String[]{"1"};
        String[] actual = convertUtil.convertDifficulty(difficulty);
        Assertions.assertThat(actual[0]).isEqualTo("FUNDAMENTAL");
    }

    @DisplayName("유형 배열을 지정한 Enum 내부의 값을 가지도록 정상적으로 변환한다.")
    @Test
    void convert_type_success() {
        String[] type = new String[]{"Digital_Course"};
        String[] actual = convertUtil.convertType(type);
        Assertions.assertThat(actual[0]).isEqualTo("Digital Course");
    }
}