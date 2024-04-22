package com.s207.cloudy.domain.learning.entity;

import com.s207.cloudy.domain.learning.entity.embedded.LearningJobPK;
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
