package com.s207.cloudy.domain.members.application;

import com.s207.cloudy.domain.members.MemberDto;
import com.s207.cloudy.domain.members.dao.MemberRepository;
import com.s207.cloudy.domain.members.entity.Member;
import com.s207.cloudy.domain.members.exception.MemberException;
import com.s207.cloudy.global.error.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Boolean isExist(String id) {
        return memberRepository.existsByMemberId(id);
    }

    @Override
    public MemberDto findById(String id) {
        return memberRepository.findById(id)
                .orElseThrow(()-> new MemberException(ErrorCode.NOT_FOUND))
                .toDto();
    }

    @Override
    public void save(Member member) {
        memberRepository.save(member);
    }
}
