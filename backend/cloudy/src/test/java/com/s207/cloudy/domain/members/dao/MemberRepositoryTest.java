package com.s207.cloudy.domain.members.dao;

import com.s207.cloudy.TestQueryDslConfig;
import com.s207.cloudy.domain.members.domain.Member;
import com.s207.cloudy.dummy.DummyMember;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.Optional;

@DataJpaTest
@Import(TestQueryDslConfig.class)
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TestEntityManager entityManager;
    Member dummyMember;

    @BeforeEach
    void setUp() {
        dummyMember = DummyMember.getDummyMember();
    }

    @DisplayName("회원 가입된 회원의 가입 여부를 정상적으로 조회한다.")
    @Test
    void get_is_member_exist_by_memberId_success_when_exist_member() {
        entityManager.persist(dummyMember);
        entityManager.flush();

        boolean actualIsExist = memberRepository.existsByMemberId(dummyMember.getId());

        Assertions.assertThat(actualIsExist).isTrue();
    }

    @DisplayName("회원 가입되지 않은 회원의 가입 여부를 정상적으로 조회한다.")
    @Test
    void get_is_member_exist_by_memberId_success_when_non_exist_member() {
        boolean actualIsExist = memberRepository.existsByMemberId(dummyMember.getId());

        Assertions.assertThat(actualIsExist).isFalse();
    }

    @DisplayName("회원 가입된 회원의 엔터티를 정상적으로 조회한다.")
    @Test
    void get_member_entity_by_memberId_success_when_exist_member() {
        entityManager.persist(dummyMember);
        entityManager.flush();

        Optional<Member> actualMember = memberRepository.findById(dummyMember.getId());

        Assertions.assertThat(actualMember).isNotEmpty();
        Assertions.assertThat(actualMember.get().getId()).isEqualTo(dummyMember.getId());
        Assertions.assertThat(actualMember.get().getName()).isEqualTo(dummyMember.getName());
    }

    @DisplayName("회원 가입되지 않은 회원의 엔터티를 정상적으로 조회한다.")
    @Test
    void get_member_entity_by_memberId_success_when_non_exist_member() {
        Optional<Member> actualMember = memberRepository.findById(dummyMember.getId());

        Assertions.assertThat(actualMember).isEmpty();
    }
}