package com.s207.cloudy.domain.chatbot.qna.application;

import static dev.langchain4j.model.openai.OpenAiModelName.GPT_3_5_TURBO;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeEmbeddingStore;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenAiService {

    @Value("${openai.key}")
    private String openAiKey;

    @Value("${pinecone.key}")
    private String pineconeKey;

    @Value("${pinecone.id.qna}")
    private String projectId;

    private PromptTemplate promptTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    @PostConstruct
    public void init() {
        promptTemplate = PromptTemplate
            .from("아래의 상황을 참고해 질문에 대한 답을 작성해줘. " +
                "만약 주어진 상황에서 질문에 대한 대답을 제공할 정보를 찾을 수 없다면 '무슨 말인지 잘 모르겠어요.' 라고 대답해줘. " +
                "답변은 항상 한국어로 해야만하고, 고객에게 응대하듯이 친절한 말투로 대답해야돼.\n" +
                "상황: {context1} {context2} {context3} \n" +
                "질문: {question} \n" +
                "답변: ");
    }

    public String ask(String question) {
//        String requestValue = getRequestValue(question);

        EmbeddingStore<TextSegment> embeddingStore = getPineconeEmbeddingStore();


        EmbeddingModel embeddingModel = getOpenAiEmbeddingModel();


        Embedding queryEmbedding = embeddingModel.embed(question).content();
        List<EmbeddingMatch<TextSegment>> relevant = embeddingStore.findRelevant(queryEmbedding, 1);
        EmbeddingMatch<TextSegment> embeddingMatch = relevant.get(0);

        log.info(String.valueOf(embeddingMatch.score()));
        log.info(embeddingMatch.embedded().text());

        Map<String, Object> variables = new HashMap<>();
        variables.put("context1", relevant.get(0).embedded().text());
//        variables.put("context2", relevant.get(1).embedded().metadata("ans"));
//        variables.put("context3", relevant.get(2).embedded().metadata("ans"));
        variables.put("question", question);
        Prompt prompt = promptTemplate.apply(variables);


        StreamingChatLanguageModel streamingChatModel =
            OpenAiStreamingChatModel.withApiKey(openAiKey);

        generateStreamingChat(streamingChatModel, prompt);

        ChatLanguageModel model = getOpenAiChatModel();
        String response = model.generate(prompt.text());

//        Flux<String> eventStream = getRetrieve(requestValue)
//                .bodyToFlux(String.class);

        return response;

    }

    private OpenAiEmbeddingModel getOpenAiEmbeddingModel() {
        return OpenAiEmbeddingModel.builder()
            .apiKey(openAiKey)
            .modelName("text-embedding-3-small")
            .build();
    }

    private void generateStreamingChat(StreamingChatLanguageModel streamingChatModel,
                                       Prompt prompt) {
        streamingChatModel.generate(prompt.text(), new StreamingResponseHandler<AiMessage>() {

            @Override
            public void onNext(String token) {
                log.info("onNext(): " + token);
            }

            @Override
            public void onComplete(Response<AiMessage> response) {
                log.info("onComplete(): " + response);
            }

            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }

    private OpenAiChatModel getOpenAiChatModel() {
        return OpenAiChatModel.builder()
            .apiKey(openAiKey)
            .modelName(GPT_3_5_TURBO)
            .maxTokens(50)
            .build();
    }

    private PineconeEmbeddingStore getPineconeEmbeddingStore() {
        return PineconeEmbeddingStore.builder()
            .apiKey(pineconeKey)
            .environment("aped-4627-b74a")
            .projectId(projectId)
            .index("qna")
            .nameSpace("question")
            .build();
    }

}
