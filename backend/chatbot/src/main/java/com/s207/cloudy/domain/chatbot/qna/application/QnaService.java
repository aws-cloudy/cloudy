package com.s207.cloudy.domain.chatbot.qna.application;

import com.s207.cloudy.domain.chatbot.common.application.ChatService;
import com.s207.cloudy.domain.chatbot.qna.dto.QuestionReq;
import com.s207.cloudy.global.infra.chat.OpenAiChatService;
import com.s207.cloudy.global.infra.embeddingstore.PineconeService;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class QnaService implements ChatService {
    private static final String INDEX_NAME = "qna";
    private static final String NAMESPACE = "question";
    private static final String METADATA_TEXT_KEY = "ans";
    private static final String EMBEDDING_MODEL_NAME = "text-embedding-3-small";


    private final PineconeService pineconeService;
    private final OpenAiChatService openAiChatService;

    @PostConstruct
    public void init() {
        // 파인콘 설정 초기화
        pineconeService.initEmbeddingStore(INDEX_NAME, NAMESPACE, METADATA_TEXT_KEY, EMBEDDING_MODEL_NAME);

    }

    @Override
    public Flux<String> generateChatStream(QuestionReq questionReq) {
        String inputData = questionReq.getInputData();

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

        return openAiChatService.generateStreamingChat(template, variables);

    }

    @Override
    public String generateChatString(QuestionReq questionReq) {
        String inputData = questionReq.getInputData();


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
