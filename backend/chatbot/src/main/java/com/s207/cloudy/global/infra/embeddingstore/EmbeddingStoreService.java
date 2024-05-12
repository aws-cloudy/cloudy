package com.s207.cloudy.global.infra.embeddingstore;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;

import java.util.List;

public interface EmbeddingStoreService {

    List<EmbeddingMatch<TextSegment>> findRelevant(String question, int maxNum);

    void addData(TextSegment segment, String text);
}
