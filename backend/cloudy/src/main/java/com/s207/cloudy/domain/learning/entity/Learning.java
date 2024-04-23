package com.s207.cloudy.domain.learning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Learning")
public class Learning {
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

    @Column(name = "desc")
    private String desc;

    @Column(name = "summary")
    private String summary;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "link")
    private String link;

    @Column(name = "type")
    private String type;

}
