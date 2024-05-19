package com.s207.cloudy.search.domain.learning.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchReq {

    private String query = "";

    private int count = 3;

}
