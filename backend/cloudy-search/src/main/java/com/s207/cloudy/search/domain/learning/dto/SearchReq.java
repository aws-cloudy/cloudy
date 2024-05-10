package com.s207.cloudy.search.domain.learning.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchReq {

    private String query = "";

    private int count = 3;

}
