package com.s207.cloudy.domain.learning.domain.embedded;

import com.s207.cloudy.domain.learning.domain.Job;
import com.s207.cloudy.domain.learning.domain.Learning;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LearningJobPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "learning_id")
    private Learning learning;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
}
