package com.s207.cloudy.domain.roadmapgroup.member.domain;

import com.s207.cloudy.domain.roadmapgroup.roadmap.domain.Roadmap;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Member_Roadmap")
@Getter
@Setter
@NoArgsConstructor
public class MemberRoadmap {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "member_id")
    private int memberId;

    @ManyToOne
    @JoinColumn(name = "roadmap_id")
    private Roadmap roadmap;

}
