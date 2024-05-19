package com.s207.cloudy.domain.roadmap_group.comment.domain;

import com.s207.cloudy.domain.members.domain.Member;
import com.s207.cloudy.domain.roadmap_group.comment.dto.RoadmapCommentDto;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "Roadmap_Comment")
@Getter
@NoArgsConstructor
@ToString
public class RoadmapComment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roadmap_id")
    private Roadmap roadmap;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "content")
    private String content;

    @Column(name = "reg_at")
    private LocalDateTime regAt;

    @Builder
    public RoadmapComment(Roadmap roadmap, Member member, String content) {
        this.roadmap = roadmap;
        this.member= member;
        this.content = content;
        this.regAt = LocalDateTime.now();
    }


    public RoadmapCommentDto toDto(){
        return RoadmapCommentDto
                .builder()
                .commentId(id)
                .member(member.toDto())
                .content(content)
                .regAt(regAt)
                .build();
    }

}
