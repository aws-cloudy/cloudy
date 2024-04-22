package com.s207.cloudy.domain.roadmapgroup.comment.domain;

import com.s207.cloudy.domain.roadmapgroup.roadmap.domain.Roadmap;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Roadmap_Comment")
@Getter
@Setter
@NoArgsConstructor
public class RoadmapComment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roadmap_id")
    private Roadmap roadmap;

    @Column(name = "member_id")
    private int memberId;

    @Column(name = "content")
    private String content;
}
