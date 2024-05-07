package com.s207.cloudy.infra.embeddingstore;

import com.s207.cloudy.infra.embeddingmodel.OpenAiEmbeddingService;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeEmbeddingStore;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PineconeService implements EmbeddingStoreService {

    @Value("${pinecone.key}")
    private String pineconeKey;

    @Value("${pinecone.id.qna}")
    private String projectId;

    @Value("${pinecone.env.qna}")
    private String projectEnv;

    private final OpenAiEmbeddingService openAiEmbeddingService;

    private EmbeddingStore<TextSegment> embeddingStore;

    @Override
    public void initEmbeddingStore(String indexName, String namespace, String metadataTextKey, String embeddingModelName) {
        // 임베딩 서비스 설정 초기화
        openAiEmbeddingService.initOpenAiEmbeddingModel(embeddingModelName);

        embeddingStore = PineconeEmbeddingStore.builder()
                .apiKey(pineconeKey)
                .environment(projectEnv)
                .projectId(projectId)
                .index(indexName)
                .nameSpace(namespace)
                .metadataTextKey(metadataTextKey)
                .build();
    }

    @Override
    public List<EmbeddingMatch<TextSegment>> findRelevant(String question, int maxNum) {
        // 사용자 입력 문자 임베딩 데이터로 변환
        Embedding queryEmbedding = openAiEmbeddingService.getEmbeddingQuery(question);
        return embeddingStore.findRelevant(queryEmbedding, maxNum);
    }

    @Override
    public void addData(TextSegment segment, String text) {
        Embedding embedding = openAiEmbeddingService.getEmbeddingQuery(text);
        embeddingStore.add(embedding, segment);
    }
}
