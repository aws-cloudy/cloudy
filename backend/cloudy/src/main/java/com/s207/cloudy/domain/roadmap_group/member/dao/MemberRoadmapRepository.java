package com.s207.cloudy.domain.roadmap_group.member.dao;

import com.s207.cloudy.domain.roadmap_group.member.domain.MemberRoadmap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoadmapRepository extends JpaRepository<MemberRoadmap, Integer> {
}
