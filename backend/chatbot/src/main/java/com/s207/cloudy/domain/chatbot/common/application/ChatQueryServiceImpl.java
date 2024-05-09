package com.s207.cloudy.domain.chatbot.common.application;

import com.s207.cloudy.domain.chatbot.entity.Chat;
import com.s207.cloudy.domain.chatbot.entity.Chatbot;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 이하늬
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class ChatQueryServiceImpl implements ChatQueryService {
    private final DynamoDbTemplate dynamoDbTemplate;

    @Override
    public Chat saveChat(String userId, Chatbot chatbot, String content,
                         boolean isUserSent) {
        Chat chat = new Chat();
        chat.setUserId(userId + "_" + chatbot.QNA.getNum());
        chat.setRegAt(Timestamp.valueOf(LocalDateTime.now()).toString());
        chat.setContent(content);
        chat.setUserSent(isUserSent);
        return dynamoDbTemplate.save(chat);
    }
}
