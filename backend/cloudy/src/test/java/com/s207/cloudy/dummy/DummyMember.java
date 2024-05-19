package com.s207.cloudy.dummy;

import com.s207.cloudy.domain.members.domain.Member;

public class DummyMember {

    public static Member getDummyMember() {
        return new Member("id", "password", "name");
    }

}
