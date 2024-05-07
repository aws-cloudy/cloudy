package com.s207.cloudy.chatbot.qna.application;

import dev.langchain4j.data.embedding.Embedding;

public interface EmbeddingService {
    void initOpenAiEmbeddingModel(String modelName);

    Embedding getEmbeddingQuery(String query);
}
