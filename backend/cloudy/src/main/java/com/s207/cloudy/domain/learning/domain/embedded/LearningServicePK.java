package com.s207.cloudy.domain.learning.domain.embedded;

import com.s207.cloudy.domain.learning.domain.Learning;
import com.s207.cloudy.domain.learning.domain.Service;
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
public class LearningServicePK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "learning_id")
    private Learning learning;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
}
