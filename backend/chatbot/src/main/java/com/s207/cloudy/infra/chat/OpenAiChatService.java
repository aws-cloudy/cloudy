package com.s207.cloudy.infra.chat;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.output.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Arrays;
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


    private void initOpenAiChatModel() {
        openAiChatModel = OpenAiChatModel.builder()
                .apiKey(openAiKey)
                .modelName(GPT_3_5_TURBO)
                .maxTokens(50)
                .build();
    }


    @Override
    public Flux<String> generateStreamingChat(String template, Map<String, Object> variables) {

        Prompt prompt = getPrompt(template, variables);

        log.debug("generate prompt: {}", prompt.text().substring(0, 15));

        if (openAiChatModel == null) {
            openAiStreamingChatModel = OpenAiStreamingChatModel.builder()
                    .apiKey(openAiKey)
                    .modelName(OpenAiChatModelName.GPT_3_5_TURBO)
                    .build();
        }

        return Flux.create(emitter ->
                openAiStreamingChatModel.generate(prompt.text(), new StreamingResponseHandler<>() {

                    @Override
                    public void onNext(String token) {
                        log.debug("onNext(): " + token);
                        emitter.next(token);
                    }

                    @Override
                    public void onComplete(Response<AiMessage> response) {
                        log.debug("onComplete(): " + response);
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

    private Prompt getPrompt(String template, Map<String, Object> variables) {
        PromptTemplate promptTemplate = PromptTemplate.from(template);
        return promptTemplate.apply(variables);
    }

}
