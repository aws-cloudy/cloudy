package com.s207.cloudy.domain.members.application;

import com.s207.cloudy.domain.members.MemberDto;
import com.s207.cloudy.domain.members.domain.Member;


public interface MemberService {
    Boolean isExist(String id);

    MemberDto findById(String id);

    void save(Member member);
}
