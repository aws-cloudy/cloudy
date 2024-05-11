package com.s207.cloudy.domain.chatbot.common.application;

import com.s207.cloudy.domain.chatbot.common.dto.ChatListRes;
import com.s207.cloudy.domain.chatbot.common.entity.Chatbot;
import com.s207.cloudy.domain.chatbot.qna.application.QnaService;
import com.s207.cloudy.domain.chatbot.common.dto.QuestionReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ChatBotServiceImpl implements ChatBotService {
    private final QnaService qnaService;
    private final ChatQueryService chatQueryService;

    @Override
    public Flux<String> question(QuestionReq question, String userId) {

        int type = question.getType();
        if (Chatbot.QNA.getNum() == type){
            chatQueryService.saveChat(userId, Chatbot.QNA,
                    question.getInputData(), true);
            return qnaService.generateChatStream(question, userId);
        }

        return null;
    }

    @Override
    public ChatListRes getChatList(int type, String userId) {
        return new ChatListRes(chatQueryService.getChatListByUserId(userId, type));
    }
}
