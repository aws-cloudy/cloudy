package com.s207.cloudy.domain.recommend.learning.application;


import com.s207.cloudy.domain.recommend.learning.dao.LearningRepository;
import com.s207.cloudy.domain.recommend.learning.dto.LearningListRes;
import com.s207.cloudy.global.infra.chatmodel.OpenAiChatService;
import com.s207.cloudy.global.infra.embeddingmodel.OpenAiEmbeddingService;
import com.s207.cloudy.global.infra.embeddingstore.EmbeddingStoreService;
import com.s207.cloudy.global.infra.embeddingstore.PineconeService;
import dev.langchain4j.store.embedding.EmbeddingStore;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.s207.cloudy.global.infra.embeddingstore.enums.EmbeddingModelName.TEXT_EMBEDDING_3_SMALL;
import static com.s207.cloudy.global.infra.embeddingstore.enums.IndexName.CLOUDY;
import static com.s207.cloudy.global.infra.embeddingstore.enums.MetadataKey.ID;
import static com.s207.cloudy.global.infra.embeddingstore.enums.NameSpace.LEARNING;

@Service
@RequiredArgsConstructor
@Slf4j
public class LearningServiceImpl {

    @Value("${pinecone.key}")
    private String pineconeKey;

    @Value("${pinecone.id.qna}")
    private String projectId;

    @Value("${pinecone.env.qna}")
    private String projectEnv;

    private EmbeddingStoreService pineconeService;
    private final OpenAiChatService openAiChatService;
    private final OpenAiEmbeddingService openAiEmbeddingService;

    private final LearningRepository learningRepository;


    @PostConstruct
    public void init(){
        this.pineconeService = new PineconeService(
                pineconeKey,
                projectId,
                projectEnv,
                CLOUDY.getName(),
                LEARNING.getName(),
                ID.getName(),
                openAiEmbeddingService
        );
    }



    //주어진 질의에 대해 키워드 추출
    private String extractKeywords(String query){
        String template= """
                        The user asked the following question:
                        "{{desc}}"
                    
                        Provide four AWS service names and four IT technology keywords related to the content.
                        Each keyword should be separated by a comma and a space, and the whole output should be in one line.
                        Do not include any labels such as "AWS services" or "IT keywords and .
                        Exclude "IAM", "AWS", "Amazon" from the AWS services."
                        Each Keyword must be an english
                        """;

        Map<String, Object> variables = new HashMap<>();

        variables.put("desc", query);

        String keywords = openAiChatService.generateChat(template, variables);

        log.info("LearningServiceImpl :: keywords = {}", keywords);


        return keywords;

    }


    public LearningListRes recommendLearning(String query, Integer num){
        String keywords = extractKeywords(query)
                .replace("AWS", "")
                .replace("Amazon", "");

        List<Integer> recommendedLearningIds = pineconeService.findRelevant(keywords, num)
                .stream()
                .map(e -> Integer.parseInt(e.embedded().text()))
                .collect(Collectors.toList());


        return LearningListRes
                .builder()
                .learningList(learningRepository.findAllByIds(recommendedLearningIds))
                .build();

    }




}
