package com.s207.cloudy.search.dummy.learning;

import com.s207.cloudy.search.domain.learning.dto.SearchListItem;
import com.s207.cloudy.search.domain.learning.dto.SearchListRes;
import com.s207.cloudy.search.domain.learning.dto.SearchQueryRes;

import java.util.List;

public class DummyLearning {

    public static SearchListItem getDummySearchItem1() {
        return SearchListItem.builder()
                .learningId("3")
                .title("Introduction to Amazon Elastic MapReduce (EMR) (Korean)")
                .documentId("3")
                .build();
    }

    public static SearchListItem getDummySearchItem2() {
        return SearchListItem.builder()
                .learningId("28")
                .title("Introduction to Amazon Elastic Container Registry (Korean)")
                .documentId("28")
                .build();
    }

    public static SearchListItem getDummySearchItem3() {
        return SearchListItem.builder()
                .learningId("29")
                .title("Introduction to Amazon Elastic Container Service (Korean)")
                .documentId("29")
                .build();
    }

    public static SearchListRes getDummySearchList(List<SearchListItem> list) {
        return SearchListRes.builder()
                .searchList(list)
                .build();
    }

    public static SearchQueryRes getDummySearchQueryWhenExist() {
        SearchQueryRes res = new SearchQueryRes();
        res.setQuery("amazon");
        return res;
    }

    public static SearchQueryRes getDummySearchQueryWhenModified() {
        SearchQueryRes res = new SearchQueryRes();
        res.setQuery("amezon");
        res.setModifiedQuery("amazon");
        return res;
    }

    public static SearchQueryRes getDummySearchQueryWhenNotExistAndNotModified() {
        SearchQueryRes res = new SearchQueryRes();
        res.setQuery("abcdefghijklmsopqrstuvwxyz");
        return res;
    }
}

