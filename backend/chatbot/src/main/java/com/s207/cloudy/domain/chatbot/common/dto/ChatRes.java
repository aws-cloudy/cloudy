package com.s207.cloudy.domain.chatbot.common.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRes {

    private int chatNo;
    private int userNo;
    private String userName;
    private String userImage;
    private String type;
    private String message;
    private LocalDateTime createdAt;
    private Boolean isRead;


}