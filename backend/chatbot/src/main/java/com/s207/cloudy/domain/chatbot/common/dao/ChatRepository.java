package com.s207.cloudy.domain.chatbot.common.dao;

import com.s207.cloudy.domain.chatbot.common.entity.Chat;
import com.s207.cloudy.domain.chatbot.common.entity.ChatId;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface ChatRepository extends CrudRepository<Chat, ChatId> {
    List<Chat> findAllByChatId_userId_OrderByRegAtDesc(String userId);
}
