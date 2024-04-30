package com.s207.cloudy.domain.roadmap_group.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookmarkListRes {
    private List<BookmarkRes> roadmaps;
    private long totalPageCnt;
}
