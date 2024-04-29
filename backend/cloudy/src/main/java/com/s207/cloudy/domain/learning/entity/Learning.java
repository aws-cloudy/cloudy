package com.s207.cloudy.domain.learning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Learning")
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
}
