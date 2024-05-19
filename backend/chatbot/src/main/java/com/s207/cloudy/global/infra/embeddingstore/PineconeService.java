package com.s207.cloudy.global.infra.embeddingstore;

import com.s207.cloudy.global.infra.embeddingmodel.OpenAiEmbeddingService;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeEmbeddingStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class PineconeService implements EmbeddingStoreService {

    private final OpenAiEmbeddingService openAiEmbeddingService;

    private EmbeddingStore<TextSegment> embeddingStore;

    public PineconeService(
            String pineconeKey, String projectId, String projectEnv,
            String indexName, String namespace, String metadataTextKey,
            OpenAiEmbeddingService openAiEmbeddingService
    ) {
        // 임베딩 서비스 설정 초기화
        embeddingStore = PineconeEmbeddingStore.builder()
                .apiKey(pineconeKey)
                .environment(projectEnv)
                .projectId(projectId)
                .index(indexName)
                .nameSpace(namespace)
                .metadataTextKey(metadataTextKey)
                .build();

        this.openAiEmbeddingService = openAiEmbeddingService;
    }

    @Override
    public List<EmbeddingMatch<TextSegment>> findRelevant(String question, int maxNum) {
        // 사용자 입력 문자 임베딩 데이터로 변환
        Embedding embeddingQuery = openAiEmbeddingService.getEmbeddingQuery(question);
        log.info("임베딩 결과 :: {}", embeddingQuery.vectorAsList());

        return embeddingStore.findRelevant(embeddingQuery, maxNum);
    }

    @Override
    public void addData(TextSegment segment, String text) {

        Embedding embedding = openAiEmbeddingService.getEmbeddingQuery(text);
        embeddingStore.add(embedding, segment);
    }
}
