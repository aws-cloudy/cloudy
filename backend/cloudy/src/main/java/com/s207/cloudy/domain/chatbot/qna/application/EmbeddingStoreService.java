package com.s207.cloudy.domain.chatbot.qna.application;

public interface EmbeddingStoreService {
    void initEmbeddingStore(String indexName, String namespace, String metadataTextKey);
}
