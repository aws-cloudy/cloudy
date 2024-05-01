package com.s207.cloudy.domain.roadmap_group.comment.application;

import com.s207.cloudy.domain.members.dao.MemberRepository;
import com.s207.cloudy.domain.members.entity.Member;
import com.s207.cloudy.domain.members.exception.MemberException;
import com.s207.cloudy.domain.roadmap_group.comment.dao.RoadmapCommentRepository;
import com.s207.cloudy.domain.roadmap_group.comment.domain.RoadmapComment;
import com.s207.cloudy.domain.roadmap_group.comment.dto.RoadmapCommentDto;
import com.s207.cloudy.domain.roadmap_group.comment.dto.RoadmapCommentPostReq;
import com.s207.cloudy.domain.roadmap_group.member.exception.MemberRoadmapNotFoundException;
import com.s207.cloudy.domain.roadmap_group.roadmap.dao.RoadmapRepository;
import com.s207.cloudy.domain.roadmap_group.roadmap.domain.Roadmap;
import com.s207.cloudy.domain.roadmap_group.roadmap.exception.RoadmapNotFoundException;
import com.s207.cloudy.global.error.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoadmapCommentServiceImpl implements RoadmapCommentService{
    private final RoadmapCommentRepository roadmapCommentRepository;
    private final RoadmapRepository roadmapRepository;
    private final MemberRepository memberRepository;
    @Override
    public List<RoadmapCommentDto> getRoadmapList(Integer roadmapId) {
        log.error("{}",roadmapCommentRepository.findByRoadmapId(roadmapId));
        return roadmapCommentRepository
                .findByRoadmapId(roadmapId)
                .stream()
                .map(RoadmapComment::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Integer postRoadmapComment(RoadmapCommentPostReq roadmapCommentPostReq) {




        Roadmap roadmap = roadmapRepository.getRoadmapsById(roadmapCommentPostReq.roadmapId())
                        .orElseThrow(RoadmapNotFoundException::new);



        //todo
        //  프론트 jwt 안정화되면 member와 jwt내부 id 비교해서 맞는지 체크하는 로직 추가해야함

        Member member = memberRepository.findById(roadmapCommentPostReq.memberId())
                .orElseThrow(()-> new MemberException(ErrorCode.NOT_FOUND));

        // RoadmapComment 객체 생성
        RoadmapComment comment = RoadmapComment.builder()
                .roadmap(roadmap)
                .member(member)
                .content(roadmapCommentPostReq.content())
                .build();

        // Repository를 통해 저장하고 저장된 엔티티 반환
        return roadmapCommentRepository.save(comment).getId();

    }
}
