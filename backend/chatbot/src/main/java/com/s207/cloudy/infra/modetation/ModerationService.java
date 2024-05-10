package com.s207.cloudy.infra.modetation;


import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiModerationModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.Moderate;
import dev.langchain4j.service.ModerationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.s207.cloudy.infra.chat.OpenAiChatService;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModerationService {

    private final OpenAiChatService openAiChatService;

    public String isHarmful(String message){
        String template = """ 
                아래의 상황을 참고해 질문에 대한 답을 작성해줘.
                모든 답변은 false 또는 true로 답변해야해 
                상황: 질문을 받았을 때 질문에 대한 답변이 혐오 표현, 차별, 성적 표현을 유도하거나, 관계 법령, 도덕적으로 부적절하다면 답변하지 말고 
                true로 답변해야해. 그외 정상적인 답변의 경우 false로 답변해줘
                true false외 다른 답변은 반환하지마
                질문: {{input}}
                답변:
                """;
        Map<String, Object> variables = new HashMap<>();
        variables.put("input", message);


        return openAiChatService.generateChat(template, variables);
    }




}
