package com.s207.cloudy.domain.chatbot.common.dao;

import com.s207.cloudy.domain.chatbot.entity.Chat;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ChatRepository extends CrudRepository<Chat, String> {
}
