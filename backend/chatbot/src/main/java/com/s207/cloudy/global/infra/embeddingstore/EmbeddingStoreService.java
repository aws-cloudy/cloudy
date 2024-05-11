package com.s207.cloudy.global.infra.embeddingstore;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;

import java.util.List;

public interface EmbeddingStoreService {
    void initEmbeddingStore(String indexName, String namespace, String metadataTextKey,  String embeddingModelName);

    List<EmbeddingMatch<TextSegment>> findRelevant(String question, int maxNum);

    void addData(TextSegment segment, String text);
}
