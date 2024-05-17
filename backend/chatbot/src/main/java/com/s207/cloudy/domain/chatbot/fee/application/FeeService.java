package com.s207.cloudy.domain.chatbot.fee.application;


import com.s207.cloudy.domain.chatbot.common.dto.ChatReq;
import com.s207.cloudy.domain.chatbot.common.entity.Chatbot;
import com.s207.cloudy.global.infra.chatmodel.OpenAiChatService;
import com.s207.cloudy.global.infra.embeddingmodel.OpenAiEmbeddingService;
import com.s207.cloudy.global.infra.embeddingstore.EmbeddingStoreService;
import com.s207.cloudy.global.infra.embeddingstore.PineconeService;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.s207.cloudy.global.infra.embeddingstore.enums.IndexName.CLOUDY;
import static com.s207.cloudy.global.infra.embeddingstore.enums.IndexName.QNA;
import static com.s207.cloudy.global.infra.embeddingstore.enums.MetadataKey.ANSWER;
import static com.s207.cloudy.global.infra.embeddingstore.enums.MetadataKey.DOCS;
import static com.s207.cloudy.global.infra.embeddingstore.enums.NameSpace.QUESTION;
import static com.s207.cloudy.global.infra.embeddingstore.enums.NameSpace.RATE;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeeService {

    @Value("${pinecone.key}")
    private String pineconeKey;

    @Value("${pinecone.id.qna}")
    private String projectId;

    @Value("${pinecone.env.qna}")
    private String projectEnv;

    private EmbeddingStoreService pineconeService;
    private final OpenAiChatService openAiChatService;
    private final OpenAiEmbeddingService openAiEmbeddingService;
    @PostConstruct
    public void init() {
        // 파인콘 설정 초기화
        this.pineconeService = new PineconeService(
                pineconeKey,
                projectId,
                projectEnv,
                "prod",
                "rate",
                "docs",
                openAiEmbeddingService
        );
    }

    public Flux<String> generateChatStream(ChatReq chatReq, String userId) {
        String inputData = chatReq.getInputData();
        log.info("{}", inputData);
        // 벡터DB에서 유사한 데이터 상위 n개 조회
        List<EmbeddingMatch<TextSegment>> relevant = pineconeService.findRelevant(inputData, 1);

        log.info("find relevant data: {}", relevant.get(0).embedded().text());

        String template = """ 
                아래의 상황을 참고해 요금 관련 질문에 대한 답을 작성해줘.
                답변은 항상 한국어로 해야만하고, 고객에게 응대하듯이 친절한 말투로 대답해야돼.
                상황에 대해서 요금 계산기 링크가 있다면 마지막에 요금 계산기 링크를 넣어줘 
                상황: 유저에게 AWS서비스 요금에 대한 질문을 받았고 관련 자료는 다음과 같아 {{context}}
                질문: {{input}}
                답변:
                """;

        Map<String, Object> variables = new HashMap<>();
        variables.put("context", relevant.get(0).embedded().text());
        variables.put("input", inputData);

        return openAiChatService.generateStreamingChat(template, variables, userId, Chatbot.FEE);
    }

}
