package com.s207.cloudy.domain.roadmapgroup.learning.domain;

import com.s207.cloudy.domain.roadmapgroup.roadmap.domain.Roadmap;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Learning_Roadmap")
@Getter
@Setter
@NoArgsConstructor
public class LearningRoadmap {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "roadmap_id")
    private Roadmap roadmap;

    @ManyToOne
    @JoinColumn(name = "learning_id")
    private Learning learning;

    @Column(name = "rank")
    private int rank;

}
