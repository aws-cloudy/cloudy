package com.s207.cloudy.domain.roadmap_group.member.dto;

public record BookmarkRes(int bookmarkId,
                          int roadmapId,
                          String title,
                          String thumbnail,
                          String service,
                          String job,
                          String summary,
                          long commentsCnt) {

}
