package com.s207.cloudy.domain.recommend.roadmap.application;

import com.s207.cloudy.domain.recommend.roadmap.dao.RoadmapRepository;
import com.s207.cloudy.domain.recommend.roadmap.dto.RoadmapListRes;
import com.s207.cloudy.global.infra.chatmodel.OpenAiChatService;
import com.s207.cloudy.global.infra.embeddingmodel.OpenAiEmbeddingService;
import com.s207.cloudy.global.infra.embeddingstore.EmbeddingStoreService;
import com.s207.cloudy.global.infra.embeddingstore.PineconeService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.List;
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


}
