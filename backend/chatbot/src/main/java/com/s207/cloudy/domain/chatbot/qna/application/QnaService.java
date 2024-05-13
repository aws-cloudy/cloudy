package com.s207.cloudy.domain.chatbot.qna.application;

import com.s207.cloudy.domain.chatbot.common.application.ChatService;
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

import static com.s207.cloudy.global.infra.embeddingstore.enums.IndexName.QNA;
import static com.s207.cloudy.global.infra.embeddingstore.enums.MetadataKey.ANSWER;
import static com.s207.cloudy.global.infra.embeddingstore.enums.NameSpace.QUESTION;

@Service
@RequiredArgsConstructor
@Slf4j
public class QnaService implements ChatService {


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
                QNA.getName(),
                QUESTION.getName(),
                ANSWER.getName(),
                openAiEmbeddingService
        );
    }

    @Override
    public Flux<String> generateChatStream(ChatReq chatReq, String userId) {
        String inputData = chatReq.getInputData();

        // 벡터DB에서 유사한 데이터 상위 n개 조회
        List<EmbeddingMatch<TextSegment>> relevant = pineconeService.findRelevant(inputData, 1);

        log.info("find relevant data: {}", relevant.get(0).embedded().text());

        String template = """ 
                아래의 상황을 참고해 질문에 대한 답을 작성해줘.
                모든 질문의 답변을 최대한 AWS(Amazon Web Service) 서비스로 해결할 수 있도록 제시해줘.
                답변은 항상 한국어로 해야만하고, 고객에게 응대하듯이 친절한 말투로 대답해야돼.
                상황: AWS 스킬빌더 사이트 내의 관련 faq 데이터는 다음과 같아. {{context}}
                질문: {{input}}
                답변:
                """;

        Map<String, Object> variables = new HashMap<>();
        variables.put("context", relevant.get(0).embedded().text());
        variables.put("input", inputData);

        return openAiChatService.generateStreamingChat(template, variables, userId, Chatbot.QNA);
    }

    @Override
    public String generateChatString(ChatReq chatReq) {
        String inputData = chatReq.getInputData();


        log.info("inputData :: {}, inputData", inputData);
        // 벡터DB에서 유사한 데이터 상위 n개 조회
        List<EmbeddingMatch<TextSegment>> relevant = pineconeService.findRelevant(inputData, 3);

        log.info("find relevant data: {}", relevant.get(0).embedded().text());


        String template = """ 
                아래의 상황을 참고해 질문에 대한 답을 작성해줘.
                모든 질문의 답변을 최대한 AWS(Amazon Web Service) 서비스로 해결할 수 있도록 제시해줘.
                답변은 항상 한국어로 해야만하고, 고객에게 응대하듯이 친절한 말투로 대답해야돼.
                상황: AWS 스킬빌더 사이트 내의 관련 faq 데이터는 다음과 같아. {{context}} 만약 이 FAQ 데이터가 질문을 해결할 수 없는 데이터일 경우 FAQ 데이터를 무시하고 직접 답변을 생성해줘 
                만약 이 FAQ 데이터가 질문을 해결할 수 있는 데이터일 경우 해당 데이터를 바탕으로 질문에 대한 답을 작성해줘   
                
                질문: {{input}}
                답변:
                """;

        Map<String, Object> variables = new HashMap<>();
        variables.put("context", relevant.get(0).embedded().text());
        variables.put("input", inputData);


        return openAiChatService.generateChat(template, variables);

    }

}
