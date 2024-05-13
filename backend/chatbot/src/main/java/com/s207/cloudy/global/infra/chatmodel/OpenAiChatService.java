package com.s207.cloudy.global.infra.chatmodel;

import com.s207.cloudy.domain.chatbot.common.application.ChatQueryService;
import com.s207.cloudy.domain.chatbot.common.entity.Chatbot;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.openai.OpenAiChatModel;

import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.output.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

import static dev.langchain4j.model.openai.OpenAiModelName.GPT_3_5_TURBO;


@Service
@Slf4j
@RequiredArgsConstructor
public class OpenAiChatService implements ChatService {
    @Value("${openai.key}")
    private String openAiKey;
    private OpenAiChatModel openAiChatModel;
    private OpenAiStreamingChatModel openAiStreamingChatModel;
    private final ChatQueryService chatQueryService;


    //주어진 질의에 대해 키워드 추출
    @Override
    public String extractKeywords(String query){
        String template= """
                        The user asked the following question:
                        "{{desc}}"
                    
                        Provide four AWS service names and four IT technology keywords related to the content.
                        Each keyword should be separated by a comma and a space, and the whole output should be in one line.
                        Do not include any labels such as "AWS services" or "IT Technology keywords and .
                        Exclude "IAM", "AWS", "Amazon" from the AWS services."
                        if question has "feature flagging" or "feature flag" then please include "AppConfig" in keywords
                        if question has "chat" please include "WebSocket" in keywords
                        if question has "AI" or "ML" or simmilar keywords, please include AWS service related With ML/DL
                        Each Keyword must be an english
                        please give me a 20 keywords
                        """;

        Map<String, Object> variables = new HashMap<>();

        variables.put("desc", query);

        String keywords = this.generateChat(template, variables);

        log.info("LearningServiceImpl :: keywords = {}", keywords);


        return keywords;

    }


    @Override
    public Flux<String> generateStreamingChat(String template, Map<String, Object> variables, String userId, Chatbot chatbot) {

        Prompt prompt = getPrompt(template, variables);

        log.info("generate prompt: {}", prompt.text().substring(0, 15));

        if (openAiStreamingChatModel == null) {
            openAiStreamingChatModel = OpenAiStreamingChatModel.builder()
                    .apiKey(openAiKey)
                    .modelName(GPT_3_5_TURBO)
                    .build();
        }



        return Flux.create(emitter ->
                openAiStreamingChatModel.generate(prompt.text(), new StreamingResponseHandler<>() {

                    @Override
                    public void onNext(String token) {
                        emitter.next(token);
                    }

                    @Override
                    public void onComplete(Response<AiMessage> response) {
                        chatQueryService.saveChat(userId, chatbot, response.content()
                                .text(), false);
                        emitter.complete();
                    }


                    @Override
                    public void onError(Throwable error) {
                        emitter.error(error);
//                        Arrays.stream(error.getStackTrace())
//                                .toList()
//                                .forEach(stackTraceElement -> log.error(stackTraceElement.toString()));
                    }
                }));
    }
    @Override
    public String generateChat(String template, Map<String, Object> variables) {

        Prompt prompt = getPrompt(template, variables);

        if (openAiChatModel == null) {
            openAiChatModel = OpenAiChatModel.builder()
                    .apiKey(openAiKey)
                    .modelName(GPT_3_5_TURBO)
                    .build();
        }
        var result =openAiChatModel.generate(prompt.text());
        log.info("generateChat :: result = {}",  result);
        return result;
    }

    private Prompt getPrompt(String template, Map<String, Object> variables) {
        PromptTemplate promptTemplate = PromptTemplate.from(template);
        return promptTemplate.apply(variables);
    }




}
