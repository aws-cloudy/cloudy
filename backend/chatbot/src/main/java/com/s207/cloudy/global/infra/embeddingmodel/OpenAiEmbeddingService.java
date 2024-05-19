package com.s207.cloudy.global.infra.embeddingmodel;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenAiEmbeddingService implements  EmbeddingService{

    private final OpenAiEmbeddingModel openAiEmbeddingModel;

    public OpenAiEmbeddingService(@Value("${openai.key}") String openAiKey) {
        openAiEmbeddingModel = OpenAiEmbeddingModel.builder()
                .apiKey(openAiKey)
                .modelName("text-embedding-3-small")
                .build();
    }

    @Override
    public Embedding getEmbeddingQuery(String query) {
        return openAiEmbeddingModel.embed(query).content();
    }

}
