package com.s207.cloudy.domain.recommend.learning.domain;

import com.s207.cloudy.domain.recommend.learning.dto.LearningItem;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Learning")
@ToString
public class Learning implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "difficulty")
    private String difficulty;

    @Column(name = "duration")
    private String duration;

    @Column(name = "`desc`", columnDefinition = "TEXT")
    private String desc;

    @Column(name = "summary")
    private String summary;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "link")
    private String link;

    @Column(name = "type")
    private String type;

    @Builder
    public Learning(String title, String difficulty, String duration, String desc, String summary, String thumbnail, String link, String type) {
        this.title = title;
        this.difficulty = difficulty;
        this.duration = duration;
        this.desc = desc;
        this.summary = summary;
        this.thumbnail = thumbnail;
        this.link = link;
        this.type = type;
    }

    public LearningItem toDto(){
        return LearningItem
                .builder()
                .learningId(id)
                .title(title)
                .difficulty(difficulty)
                .duration(duration)
                .desc(desc)
                .summary(summary)
                .thumbnail(thumbnail)
                .link(link)
                .build();
    }
}
