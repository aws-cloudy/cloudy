package com.s207.cloudy.domain.roadmapGroup.roadmap.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Roadmap")
@Getter
@NoArgsConstructor
public class Roadmap {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "service")
    private String service;

    @Column(name = "job")
    private String job;

    @Column(name = "summary")
    private String summary;

    @Builder
    public Roadmap(String title, String thumbnail, String service, String job, String summary) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.service = service;
        this.job = job;
        this.summary = summary;
    }
}
