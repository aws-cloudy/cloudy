package com.s207.cloudy.chatbot.qna.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class CompletionReq {
    private final String model = "gpt-3.5-turbo";
    private List<Message> messages = new ArrayList<>();
    private final boolean stream = true;

    public CompletionReq(String content) {
        messages.add(new Message(content));
    }


}

/*
{
        "model": "gpt-3.5-turbo",
        "messages": [{
        "role": "user",
        "content": "안녕"
        }],
        "stream": true
        }

*/
