package com.s207.cloudy.domain.chatbot.qna.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Choice {
    @JsonProperty("finish_reason")
    private String finishReason;
    private Delta delta;
}