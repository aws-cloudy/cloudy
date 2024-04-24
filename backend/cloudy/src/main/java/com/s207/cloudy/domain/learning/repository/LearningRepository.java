package com.s207.cloudy.domain.learning.repository;

import com.s207.cloudy.domain.learning.entity.Learning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface LearningRepository extends JpaRepository<Learning, Integer>, LearningRepositoryCustom {


}
