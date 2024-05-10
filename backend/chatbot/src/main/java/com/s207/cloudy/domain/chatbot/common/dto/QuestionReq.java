package com.s207.cloudy.domain.chatbot.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionReq {
    private String type;
    private String inputData;
}

