package com.s207.cloudy.domain.roadmap_group.member.api;


import com.s207.cloudy.domain.members.domain.Member;
import com.s207.cloudy.domain.roadmap_group.member.application.MemberRoadmapService;
import com.s207.cloudy.domain.roadmap_group.member.dto.BookmarkListRes;
import com.s207.cloudy.domain.roadmap_group.member.dto.CreateRoadmapReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/bookmarks")
public class MemberRoadmapController {
    private final MemberRoadmapService memberRoadmapService;

    @GetMapping
    public ResponseEntity<BookmarkListRes> getMyRoadmapList(@AuthenticationPrincipal Member member) {

        return ResponseEntity
                .status(OK)
                .body(memberRoadmapService.findRoadmapListByMember(member));
    }

    @PostMapping
    public ResponseEntity<Void> createMyRoadmap(@AuthenticationPrincipal Member member,
                                                @Valid @RequestBody CreateRoadmapReq req) {

        memberRoadmapService.createRoadmapBookmark(member, req);

        return ResponseEntity
                .status(CREATED)
                .build();
    }

    @DeleteMapping("/{bookmarkId}")
    public ResponseEntity<Void> deleteMyRoadMap(@PathVariable int bookmarkId) {

        memberRoadmapService.deleteById(bookmarkId);

        return ResponseEntity
                .status(NO_CONTENT)
                .build();
    }

}
