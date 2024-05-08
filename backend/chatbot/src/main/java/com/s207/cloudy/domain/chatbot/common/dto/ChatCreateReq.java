package com.s207.cloudy.domain.chatbot.common.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ChatCreateReq {

    private int chatRoomNo;
    private String inputData;
    private LocalDateTime regAt;
}