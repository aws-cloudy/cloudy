package com.s207.cloudy.domain.chatbot.qna.application;

import com.s207.cloudy.domain.chatbot.qna.dto.QuestionReq;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class QnaService {
    private static final String INDEX_NAME = "qna";
    private static final String NAMESPACE = "question";
    private static final String METADATA_TEXT_KEY = "ans";
    private static final String EMBEDDING_MODEL_NAME = "text-embedding-3-small";


    private final PineconeService pineconeService;
    private final OpenAiEmbeddingService openAiEmbeddingService;
    private final OpenAiChatService openAiChatService;

    @PostConstruct
    public void init() {
        // 파인콘 설정 초기화
        pineconeService.initEmbeddingStore(INDEX_NAME, NAMESPACE, METADATA_TEXT_KEY);
        // 임베딩 서비스 설정 초기화
        openAiEmbeddingService.initOpenAiEmbeddingModel(EMBEDDING_MODEL_NAME);
    }

    public String generateChat(QuestionReq questionReq) {
        String question = questionReq.getQuestion();

        // 사용자 입력 문자 임베딩 데이터로 변환
        Embedding queryEmbedding = openAiEmbeddingService.getEmbeddingQuery(question);

        // 벡터DB에서 유사한 데이터 상위 n개 조회
        List<EmbeddingMatch<TextSegment>> relevant = pineconeService.findRelevant(queryEmbedding, 1);


        EmbeddingMatch<TextSegment> embeddingMatch = relevant.get(0);
        log.info(String.valueOf(embeddingMatch.score()));
        log.info(embeddingMatch.embedded().text());

        String template = """ 
                아래의 상황을 참고해 질문에 대한 답을 작성해줘.
                모든 질문의 답변을 최대한 AWS(Amazon Web Service) 서비스로 해결할 수 있도록 제시해줘.
                답변은 항상 한국어로 해야만하고, 고객에게 응대하듯이 친절한 말투로 대답해야돼.
                상황: AWS 스킬빌더 사이트 내의 관련 faq 데이터는 다음과 같아. {{context1}}
                질문: {{question}}
                답변:
                """;

        Map<String, Object> variables = new HashMap<>();
        variables.put("context1", relevant.get(0).embedded().text());
//        variables.put("context2", relevant.get(1).embedded().metadata("ans"));
//        variables.put("context3", relevant.get(2).embedded().metadata("ans"));
        variables.put("question", question);

        openAiChatService.generateStreamingChat(template, variables);

//        openAiChatService.initOpenAiChatModel();
//        String response = model.generate(prompt.text());

//        Flux<String> eventStream = getRetrieve(requestValue)
//                .bodyToFlux(String.class);

//        return response;

        return null;
    }

}
