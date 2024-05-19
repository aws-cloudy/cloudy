package com.s207.cloudy.domain.recommend.roadmap.application;

import com.s207.cloudy.domain.chatbot.common.dto.ChatReq;
import com.s207.cloudy.domain.chatbot.common.entity.Chatbot;
import com.s207.cloudy.domain.recommend.exception.RecommendException;
import com.s207.cloudy.domain.recommend.roadmap.dao.RoadmapRepository;
import com.s207.cloudy.domain.recommend.roadmap.dto.RoadmapListRes;
import com.s207.cloudy.global.error.enums.ErrorCode;
import com.s207.cloudy.global.infra.chatmodel.OpenAiChatService;
import com.s207.cloudy.global.infra.embeddingmodel.OpenAiEmbeddingService;
import com.s207.cloudy.global.infra.embeddingstore.EmbeddingStoreService;
import com.s207.cloudy.global.infra.embeddingstore.PineconeService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.s207.cloudy.global.infra.embeddingstore.enums.IndexName.CLOUDY;
import static com.s207.cloudy.global.infra.embeddingstore.enums.MetadataKey.ID;
import static com.s207.cloudy.global.infra.embeddingstore.enums.NameSpace.ROADMAP;


@Service
@RequiredArgsConstructor
@Slf4j
public class RoadmapServiceImpl {


    @Value("${pinecone.key}")
    private String pineconeKey;

    @Value("${pinecone.id.qna}")
    private String projectId;

    @Value("${pinecone.env.qna}")
    private String projectEnv;


    private EmbeddingStoreService pineconeService;
    private final OpenAiChatService openAiChatService;
    private final OpenAiEmbeddingService openAiEmbeddingService;
    private final RoadmapRepository roadmapRepository;

    @PostConstruct
    public void init(){
        this.pineconeService = new PineconeService(
                pineconeKey,
                projectId,
                projectEnv,
                CLOUDY.getName(),
                "roadmap",
                ID.getName(),
                openAiEmbeddingService
        );
    }

    private List<Integer> getRecommendedRoadmapIds(String keywords, int num){
        return pineconeService.findRelevant(keywords, num)
                .stream()
                .map(e -> Integer.parseInt(e.embedded().text()))
                .collect(Collectors.toList());
    }



    public RoadmapListRes recommendRoadmap(String query, Integer num){
        String keywords = openAiChatService.extractKeywords(query)
                .replace("AWS", "")
                .replace("Amazon", "");

        List<Integer> recommendedRoadmapIds = getRecommendedRoadmapIds(keywords, num);

        log.info("recommendRoadmap ::roadmapIds{}", recommendedRoadmapIds);

        return RoadmapListRes
                .builder()
                .roadmaps(roadmapRepository.findAllByIds(recommendedRoadmapIds))
                .build();

    }

    public Flux<String> generateChatStream(ChatReq chatReq, String userId) {
        String keywords = openAiChatService.extractKeywords(chatReq.getInputData())
                .replace("AWS", "")
                .replace("Amazon", "");

        List<Integer> recommendedRoadmapIds = getRecommendedRoadmapIds(keywords, 1);

        var recommendedRoadmap = roadmapRepository
                .findById(recommendedRoadmapIds.get(0))
                .orElseThrow(()-> new RecommendException(ErrorCode.NOT_FOUND));

        String template = """ 
                아래의 상황을 참고해 질문에 대한 답을 작성해줘.
                
                상황: aws 강의에 대한 추천 결과는 다음과 같아.
                    title : {{title}}
                    desc : {{desc}} 
                     강의 정보(desc)와 질문을 바탕으로 추천한 이유를 만들어줘 
                     마지막은 링크를 생성해야해 
                     http://aws-cloudy.com/roadmap/{{id}} 
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
        variables.put("title", recommendedRoadmap.getTitle());
        variables.put("desc", recommendedRoadmap.getDesc());
        variables.put("input", chatReq.getInputData());
        variables.put("id", recommendedRoadmap.getId());

        return openAiChatService.generateStreamingChat(template, variables, userId, Chatbot.ROADMAP);
    }



}
