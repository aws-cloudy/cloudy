package com.s207.cloudy.domain.recommend.learning.dao;

import com.s207.cloudy.domain.recommend.learning.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobRepository extends JpaRepository<Job, Integer> {
    @Query("SELECT COUNT(j) > 0 FROM Job j WHERE j.id = :jobId")
    boolean existsJobId(int jobId);
}
