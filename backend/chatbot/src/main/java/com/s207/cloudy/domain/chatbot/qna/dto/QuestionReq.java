package com.s207.cloudy.domain.chatbot.qna.dto;

import com.s207.cloudy.domain.chatbot.domain.Chatbot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.N;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionReq {
    private String type;
    private String inputData;
}

