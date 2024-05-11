package com.s207.cloudy.domain.chatbot.common.application;

import com.s207.cloudy.domain.chatbot.common.dao.ChatRepository;
import com.s207.cloudy.domain.chatbot.common.dto.ChatRes;
import com.s207.cloudy.domain.chatbot.common.entity.Chat;
import com.s207.cloudy.domain.chatbot.common.entity.Chatbot;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/*
 * @author 이하늬
 * @since 1.0
 */


@Service
@RequiredArgsConstructor
public class ChatQueryServiceImpl implements ChatQueryService {
    private final ChatRepository chatRepository;


    /**
     * {@inheritDoc}
     */
    @Override
    public Chat saveChat(String userId, Chatbot chatbot, String content, boolean isUserSent) {
        Chat chat = new Chat();
        chat.setUserId(getTableKey(userId, chatbot.getNum()));
        chat.setRegAt(Timestamp.valueOf(LocalDateTime.now()).toString());
        chat.setContent(content);
        chat.setUserSent(isUserSent);
        return chatRepository.save(chat);
    }

    @Override
    public List<ChatRes> getChatListByUserId(String userId, int type) {
        return chatRepository.findAllByChatId_userId_OrderByRegAtDesc(getTableKey(userId, type))
                .stream()
                .map(ChatRes::of)
                .toList();
    }

    @NotNull
    private String getTableKey(String userId, int type) {
        return userId + "_" + type;
    }
}
