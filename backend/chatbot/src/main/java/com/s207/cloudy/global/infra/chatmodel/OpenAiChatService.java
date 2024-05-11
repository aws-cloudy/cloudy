package com.s207.cloudy.global.infra.chatmodel;

import com.s207.cloudy.domain.chatbot.common.application.ChatQueryService;
import com.s207.cloudy.domain.chatbot.entity.Chatbot;
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

    @Override
    public Flux<String> generateStreamingChat(String template, Map<String, Object> variables, String userId) {

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
                        chatQueryService.saveChat(userId, Chatbot.QNA, response.content()
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

        log.info("generate prompt: {}", prompt.text().substring(0, 15));

        if (openAiChatModel == null) {
            openAiChatModel = OpenAiChatModel.builder()
                    .apiKey(openAiKey)
                    .modelName(GPT_3_5_TURBO)
                    .build();
        }
        var result =openAiChatModel.generate(prompt.text());
        log.info("generateChat :: input={}, result = {}", prompt.text(), result);
        return result;
    }

    private Prompt getPrompt(String template, Map<String, Object> variables) {
        PromptTemplate promptTemplate = PromptTemplate.from(template);
        return promptTemplate.apply(variables);
    }




}
