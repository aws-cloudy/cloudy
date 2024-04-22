package com.s207.cloudy.domain.roadmapgroup.roadmap.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Roadmap")
@Getter
@Setter
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
}
