package com.s207.cloudy.domain.chatbot.common.application;

import com.s207.cloudy.domain.chatbot.common.dto.ChatListRes;
import com.s207.cloudy.domain.chatbot.common.entity.Chatbot;
import com.s207.cloudy.domain.chatbot.qna.application.QnaService;
import com.s207.cloudy.domain.chatbot.common.dto.ChatReq;
import com.s207.cloudy.domain.recommend.learning.application.LearningServiceImpl;
import com.s207.cloudy.domain.recommend.learning.domain.LearningService;
import com.s207.cloudy.domain.recommend.roadmap.application.RoadmapServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ChatBotServiceImpl implements ChatBotService {
    private final QnaService qnaService;

    private final ChatQueryService chatQueryService;
    private final LearningServiceImpl learningService;
    private final RoadmapServiceImpl roadmapService;



    @Override
    public Flux<String> question(ChatReq question, String userId) {

        int type = question.getType();
        if (Chatbot.QNA.getNum() == type){
            chatQueryService.saveChat(userId, Chatbot.QNA,
                    question.getInputData(), true);
            return qnaService.generateChatStream(question, userId);
        }else if(Chatbot.LEARNING.getNum()==type){
            chatQueryService.saveChat(userId, Chatbot.LEARNING,
                    question.getInputData(), true);
            return learningService.generateChatStream(question, userId);
        }else if(Chatbot.ROADMAP.getNum()==type){
            chatQueryService.saveChat(userId, Chatbot.ROADMAP,
                    question.getInputData(), true);

            return roadmapService.generateChatStream(question, userId);
        }

        return null;
    }

    @Override
    public ChatListRes getChatList(int type, String userId) {
        return new ChatListRes(chatQueryService.getChatListByUserId(userId, type));
    }
}
