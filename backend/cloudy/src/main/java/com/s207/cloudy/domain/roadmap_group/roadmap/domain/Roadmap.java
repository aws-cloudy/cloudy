package com.s207.cloudy.domain.roadmap_group.roadmap.domain;

import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapDetailsRes;
import com.s207.cloudy.domain.roadmap_group.roadmap.dto.RoadmapRes;
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

    @Column(name="desc")
    private String desc;

    

    @Builder
    public Roadmap(String title, String thumbnail, String service, String job, String summary, String desc) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.service = service;
        this.job = job;
        this.summary = summary;
        this.desc = desc;
    }

    public RoadmapRes toDto(){
        return RoadmapRes
                .builder()
                .roadmapId(id)
                .title(title)
                .thumbnail(thumbnail)
                .job(job)
                .service(service)
                .summary(summary)
                .commentsCnt(0)
                .desc(desc)
                .build();
    }
}
