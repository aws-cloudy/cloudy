package com.s207.cloudy.chatbot.qna.application;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenAiEmbeddingService implements  EmbeddingService{

    @Value("${openai.key}")
    private String openAiKey;

    private OpenAiEmbeddingModel openAiEmbeddingModel;


    @Override
    public void initOpenAiEmbeddingModel(String modelName) {
        openAiEmbeddingModel = OpenAiEmbeddingModel.builder()
                .apiKey(openAiKey)
                .modelName(modelName)
                .build();
    }

    @Override
    public Embedding getEmbeddingQuery(String query) {
        return openAiEmbeddingModel.embed(query).content();
    }

}
