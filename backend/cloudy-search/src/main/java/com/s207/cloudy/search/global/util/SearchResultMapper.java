package com.s207.cloudy.search.global.util;

import com.s207.cloudy.search.domain.learning.dto.SearchListItem;
import com.s207.cloudy.search.domain.learning.dto.SearchListRes;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.search.SearchHit;
import org.opensearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SearchResultMapper {

    public SearchListRes mapSearchResponse(SearchResponse searchResponse) {
        List<SearchListItem> searchList = new ArrayList<>();

        // 검색 결과 추출
        SearchHit[] hits = searchResponse.getHits().getHits();

        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            String title = sourceAsMap.get("title").toString();

            SearchListItem item = SearchListItem.builder()
                    .learningId(Integer.parseInt(hit.getId()))
                    .title(title)
                    .build();
            searchList.add(item);
        }

        return SearchListRes.builder()
                .searchList(searchList)
                .build();
    }

    public String mapSearchModifiedResponse(SearchResponse searchResponse, String query) {
        if (searchResponse.getHits().getHits().length <= 0) {
            return query;
        };

        SearchHit hit = searchResponse.getHits().getHits()[0];

        HighlightField highlightField = hit.getHighlightFields().get("title");
        String highlightedTitle = highlightField.getFragments()[0].string();
        System.out.println("Highlighted Title: " + highlightedTitle);

        return highlightedTitle;
    }
}
