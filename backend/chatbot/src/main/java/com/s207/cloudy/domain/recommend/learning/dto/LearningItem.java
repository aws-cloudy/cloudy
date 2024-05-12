package com.s207.cloudy.domain.recommend.learning.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.s207.cloudy.domain.recommend.learning.domain.Learning;
import lombok.*;

@Getter
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LearningItem {
    private int learningId;
    private String thumbnail;
    private String serviceType;
    private String title;
    private String summary;
    private String duration;
    private String difficulty;
    private String link;

    @Builder
    public LearningItem(int learningId, String title, String difficulty, String duration, String desc, String summary, String thumbnail, String link, String serviceType) {
        this.learningId = learningId;
        this.title = title;
        this.difficulty = difficulty;
        this.duration = duration;
        this.summary = summary;
        this.thumbnail = thumbnail;
        this.link = link;
        this.serviceType = serviceType;
    }

    public static LearningItem of(Learning learning) {
        return LearningItem.builder()
                .learningId(learning.getId())
                .title(learning.getTitle())
                .difficulty(learning.getDifficulty())
                .duration(learning.getDuration())
                .summary(learning.getSummary())
                .thumbnail(learning.getThumbnail())
                .link(learning.getLink())
                .serviceType(learning.getType())
                .build();
    }
}
