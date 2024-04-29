package com.s207.cloudy.dummy;

import com.s207.cloudy.domain.members.entity.Member;

import java.util.ArrayList;

public class DummyMember {

    public static Member getDummyMember() {
        return new Member("id", "password", new ArrayList<>());
    }

}
