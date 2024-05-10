package com.s207.cloudy.domain.roadmap_group.member.dao;

import com.s207.cloudy.domain.roadmap_group.member.domain.MemberRoadmap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRoadmapRepository extends JpaRepository<MemberRoadmap, Integer> {


    @Query("SELECT CASE WHEN count(mr)> 0 THEN true ELSE false END "+
            "FROM MemberRoadmap mr "+
            "WHERE mr.roadmap.id = :roadmapId AND mr.memberId = :memberId")
    boolean existsByRoadmapIdAndMemberId(String memberId, Integer roadmapId);
}
