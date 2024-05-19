package com.s207.cloudy.domain.roadmap_group.member.domain;

import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member_roadmap")
@Getter
@NoArgsConstructor
public class MemberRoadmap {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "member_id")
    private String memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roadmap_id")
    private Roadmap roadmap;

    public MemberRoadmap(String memberId, Roadmap roadmap) {
        this.memberId = memberId;
        this.roadmap = roadmap;
    }

}
