package com.s207.cloudy.domain.chatbot.qna.application;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeEmbeddingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PineconeService implements EmbeddingStoreService {

    @Value("${pinecone.key}")
    private String pineconeKey;

    @Value("${pinecone.id.qna}")
    private String projectId;

    @Value("${pinecone.env.qna}")
    private String projectEnv;


    private EmbeddingStore<TextSegment> embeddingStore;

    @Override
    public void initEmbeddingStore(String indexName, String namespace, String metadataTextKey) {
        embeddingStore = PineconeEmbeddingStore.builder()
                .apiKey(pineconeKey)
                .environment(projectEnv)
                .projectId(projectId)
                .index(indexName)
                .nameSpace(namespace)
                .metadataTextKey(metadataTextKey)
                .build();
    }

    public List<EmbeddingMatch<TextSegment>> findRelevant(Embedding queryEmbedding, int maxNum) {
        return embeddingStore.findRelevant(queryEmbedding, maxNum);

    }
}
