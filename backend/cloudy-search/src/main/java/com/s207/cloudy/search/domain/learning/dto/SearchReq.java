package com.s207.cloudy.search.domain.learning.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchReq {
    private String query;
    private int count;
}
