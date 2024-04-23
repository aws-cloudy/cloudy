package com.s207.cloudy.domain.roadmapgroup.comment.domain;

import com.s207.cloudy.domain.roadmapgroup.roadmap.domain.Roadmap;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Roadmap_Comment")
@Getter
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

    @Builder
    public RoadmapComment(Roadmap roadmap, int memberId, String content) {
        this.roadmap = roadmap;
        this.memberId = memberId;
        this.content = content;
    }
}
