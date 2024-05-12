package com.s207.cloudy.domain.recommend.learning.domain;

import com.s207.cloudy.domain.recommend.learning.domain.embedded.LearningJobPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Learning_Job")
public class LearningJob {
    @EmbeddedId
    private LearningJobPK learningJobPK;
}
