package com.s207.cloudy.domain.roadmap_group.member.dto;

import java.util.List;

public record BookmarkListRes(List<BookmarkRes> roadmaps,
                              long totalPageCnt) {

}
