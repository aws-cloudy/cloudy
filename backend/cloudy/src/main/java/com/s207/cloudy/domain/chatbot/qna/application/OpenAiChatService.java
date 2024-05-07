package com.s207.cloudy.domain.chatbot.qna.application;

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
    public void generateStreamingChat(String template, Map<String, Object> variables) {

        Prompt prompt = getPrompt(template, variables);

        if (openAiChatModel == null) {
            openAiStreamingChatModel = OpenAiStreamingChatModel.builder()
                    .apiKey(openAiKey)
                    .modelName(OpenAiChatModelName.GPT_3_5_TURBO)
                    .build();
        }
        openAiStreamingChatModel.generate(prompt.text(), new StreamingResponseHandler<>() {

            @Override
            public void onNext(String token) {

//                Flux<String> just = Flux.just(token);
                log.info("onNext(): " + token);
            }

            @Override
            public void onComplete(Response<AiMessage> response) {
                log.info("onComplete(): " + response);
            }

            @Override
            public void onError(Throwable error) {
                Arrays.stream(error.getStackTrace())
                        .toList()
                        .forEach(stackTraceElement -> log.error(stackTraceElement.toString()));
            }
        });
    }

    private Prompt getPrompt(String template, Map<String, Object> variables) {
        PromptTemplate promptTemplate = PromptTemplate.from(template);
        return promptTemplate.apply(variables);
    }

}
