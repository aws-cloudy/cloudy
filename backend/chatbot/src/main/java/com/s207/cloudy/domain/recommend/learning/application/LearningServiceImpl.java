package com.s207.cloudy.domain.recommend.learning.application;


import com.amazonaws.services.dynamodbv2.xspec.L;
import com.s207.cloudy.domain.chatbot.common.dto.ChatReq;
import com.s207.cloudy.domain.chatbot.common.entity.Chatbot;
import com.s207.cloudy.domain.recommend.exception.RecommendException;
import com.s207.cloudy.domain.recommend.learning.dao.LearningRepository;
import com.s207.cloudy.domain.recommend.learning.domain.Learning;
import com.s207.cloudy.domain.recommend.learning.dto.LearningListRes;
import com.s207.cloudy.global.error.enums.ErrorCode;
import com.s207.cloudy.global.infra.chatmodel.OpenAiChatService;
import com.s207.cloudy.global.infra.embeddingmodel.OpenAiEmbeddingService;
import com.s207.cloudy.global.infra.embeddingstore.EmbeddingStoreService;
import com.s207.cloudy.global.infra.embeddingstore.PineconeService;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.*;
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




    private List<Integer> getRecommendedLearningIds(String keywords, int num){
        return pineconeService.findRelevant(keywords, num)
                .stream()
                .map(e -> Integer.parseInt(e.embedded().text()))
                .collect(Collectors.toList());
    }


    public LearningListRes recommendLearning(String query, Integer num){
        String keywords = openAiChatService.extractKeywords(query)
                .replace("AWS", "")
                .replace("Amazon", "");

        List<Integer> recommendedLearningIds = getRecommendedLearningIds(keywords, num);


        return LearningListRes
                .builder()
                .learningList(learningRepository.findAllByIds(recommendedLearningIds))
                .build();

    }

    public Flux<String> generateChatStream(ChatReq chatReq, String userId) {
        String keywords = openAiChatService.extractKeywords(chatReq.getInputData())
                .replace("AWS", "")
                .replace("Amazon", "");

        List<Integer> recommendedLearningIds = getRecommendedLearningIds(keywords, 1);

        var recommendedLearning = learningRepository
                                .findById(recommendedLearningIds.get(0))
                                .orElseThrow(()-> new RecommendException(ErrorCode.NOT_FOUND));

        String template = """ 
                아래의 상황을 참고해 질문에 대한 답을 작성해줘.
                
                상황: aws 강의에 대한 추천 결과는 다음과 같아.
                    title : {{title}}
                    desc : {{desc}} 
                     강의 정보(desc)와 질문을 바탕으로 추천한 이유를 만들어줘 
                     마지막은 링크를 생성해야해 
                     http://aws-cloudy.com/learning?query=
                     링크 양식은 다음과 같고 query뒤에는 {{title}}을 붙이면 돼. 
                     링크를 만들어 낼때만 {{title}}에 space가 있을 경우 전부 %로 치환해야해 
                    최종 답변 양식은 다음과 같아 
                    {{title}}을 추천 드려요!
                    추천 드리는 이유는 다음과 같아요 
                    (추천 이유 설명하기)  
                    [바로가기](만들어낸 링크를 바탕으로 하이퍼 링크 형태로 만들기) 
                    하이퍼 링크의 []안의 내용은 바로가기로 고정해줘 
                    
                    
                질문: {{input}}
                답변:
                """;

        Map<String, Object> variables = new HashMap<>();
        variables.put("title", recommendedLearning.getTitle());
        variables.put("desc", recommendedLearning.getDesc());
        variables.put("input", chatReq.getInputData());

        return openAiChatService.generateStreamingChat(template, variables, userId, Chatbot.LEARNING);
    }




}
