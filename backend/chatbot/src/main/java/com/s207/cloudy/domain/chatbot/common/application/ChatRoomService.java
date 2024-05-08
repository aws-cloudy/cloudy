package com.s207.cloudy.domain.chatbot.common.application;

import com.s207.cloudy.domain.chatbot.common.dto.ChatRes;
import com.s207.cloudy.domain.chatbot.common.dto.ChatRoomCreateReq;
import com.s207.cloudy.domain.chatbot.common.dto.ChatRoomDetailRes;
import com.s207.cloudy.domain.chatbot.common.dto.ChatRoomListRes;
import com.s207.cloudy.domain.chatbot.common.dto.ChatRoomRes;
import java.util.List;
import org.springframework.security.core.Authentication;

public interface ChatRoomService {
    ChatRoomRes createChatRoom(ChatRoomCreateReq req, Authentication authentication);


    List<ChatRes> getChatHistoryByChatRoomNo(int chatRoomNo);

    List<ChatRoomListRes> getChatRoomList(String type, int value);

    ChatRoomDetailRes getChatRoomDetail(int chatRoomNo);

}
