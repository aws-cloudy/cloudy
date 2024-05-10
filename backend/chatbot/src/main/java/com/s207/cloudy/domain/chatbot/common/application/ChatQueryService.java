package com.s207.cloudy.domain.chatbot.common.application;

import com.s207.cloudy.domain.chatbot.common.dto.ChatRes;
import com.s207.cloudy.domain.chatbot.entity.Chat;
import com.s207.cloudy.domain.chatbot.entity.Chatbot;

import java.util.List;

/*
 * @author 이하늬
 * @since 1.0
 */


public interface ChatQueryService {
    /**
     * 채팅 내역을 저장하는 메서드입니다.
     *
     * @param userId     보낸 유저의 ID
     * @param chatbot    챗봇 타입 enum 클래스
     * @param content    채팅 내용
     * @param isUserSent 채팅 생성자의 유저/openAi모델 여부 -> true: 유저, false: openAI 모델
     * @return
     */
    Chat saveChat(String userId, Chatbot chatbot, String content, boolean isUserSent);

    List<ChatRes> getChatListByUserId(String userId, int type);
}
