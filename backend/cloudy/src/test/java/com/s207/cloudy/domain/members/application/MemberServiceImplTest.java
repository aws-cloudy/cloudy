package com.s207.cloudy.domain.members.application;

import com.s207.cloudy.domain.members.dao.MemberRepository;
import com.s207.cloudy.domain.members.domain.Member;
import com.s207.cloudy.domain.members.dto.MemberDto;
import com.s207.cloudy.domain.members.exception.MemberException;
import com.s207.cloudy.dummy.DummyMember;
import com.s207.cloudy.global.error.enums.ErrorCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringJUnitConfig(MemberServiceImpl.class)
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @MockBean
    MemberRepository mockMemberRepository;
    Member dummyMember;

    @BeforeEach
    void setUp() {
        dummyMember = DummyMember.getDummyMember();
    }

    @Test
    @DisplayName("회원 아이디로 회원 가입 여부를 정상적으로 조회한다.")
    void return_is_exist_member_by_memberId_success() {

        // given
        given(mockMemberRepository.existsByMemberId(anyString()))
                .willReturn(false);

        // when
        Boolean actualIsExist = memberService.isExist(dummyMember.getId());

        // then
        Assertions.assertThat(actualIsExist).isFalse();
    }

    @Test
    @DisplayName("회원 아이디로 회원 엔터티를 정상적으로 조회한다.")
    void return_member_entity_by_memberId_success() {

        // given
        given(mockMemberRepository.findById(anyString()))
                .willReturn(Optional.ofNullable(dummyMember));

        // when
        Member actualMember = memberService.findMemberEntity(dummyMember.getId());

        // then
        Assertions.assertThat(actualMember).isNotNull();
        Assertions.assertThat(actualMember.getName()).isEqualTo(dummyMember.getName());
    }

    @Test
    @DisplayName("존재하지 않는 회원의 아이디로 회원 엔터티 조회 시 예외를 터뜨린다.")
    void return_member_entity_by_memberId_failed() {

        // given
        given(mockMemberRepository.findById(anyString()))
                .willReturn(Optional.empty());

        // when
        // then
        assertThatExceptionOfType(MemberException.class)
                .isThrownBy(() -> memberService.findMemberEntity("id"))
                .withMessageContaining(ErrorCode.NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("회원 아이디로 회원 상세 정보를 정상적으로 조회한다.")
    void return_member_details_by_memberId_success() {

        // given
        given(mockMemberRepository.findById(anyString()))
                .willReturn(Optional.ofNullable(dummyMember));

        // when
        MemberDto actualMemberDto = memberService.findById(dummyMember.getId());

        // then
        Assertions.assertThat(actualMemberDto).isNotNull();
        Assertions.assertThat(actualMemberDto.name()).isEqualTo(dummyMember.getName());
        Assertions.assertThat(actualMemberDto.id()).isEqualTo(dummyMember.getId());
    }

    @Test
    @DisplayName("회원 정보를 정상적으로 저장한다.")
    void save_member_entity_success() {

        // given
        given(mockMemberRepository.save(any(Member.class))).willReturn(dummyMember);

        // when
        // then
        Assertions.assertThatNoException()
                .isThrownBy(() -> memberService.save(dummyMember));
    }
}