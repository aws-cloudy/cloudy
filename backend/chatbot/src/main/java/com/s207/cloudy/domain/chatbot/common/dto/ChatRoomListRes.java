package com.s207.cloudy.domain.chatbot.common.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ChatRoomListRes {
    private int chatRoomNo;
    private int dogNo;
    private String dogName;
    private String id;
    private String name;
    private String memberProfileImage;
    private int memberNo;
    private String memberName;
    private String shelterName;
    private String shelterProfileImage;
    private Boolean isPromiseAccepted;
    private LocalDateTime promiseCreatedAt;
    private LastChat lastChat;

    @AllArgsConstructor
    @Getter
    public static class LastChat {
        private String message;
        private String createdAt;
    }
}
