package com.s207.cloudy.domain.chatbot.common.api;

import com.s207.cloudy.domain.chatbot.common.application.ChatService;
import com.s207.cloudy.domain.chatbot.common.dto.ChatCreateReq;
import com.s207.cloudy.domain.chatbot.common.dto.ChatRes;
import java.security.Principal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final ChatService chatService;
    private final SimpMessageSendingOperations template;

    private static final String DESTINATION = "/api/v1/chats/sub/rooms/";

    @MessageMapping("/chat/enter")
    public void enterChatRoom(@Payload ChatCreateReq chatReq, Principal principal) {
        log.info("chatReq: {}, enter user: {}", chatReq.toString(), principal.getName());
        String msg = principal.getName() + "님이 채팅을 시작하였습니다.";
        template.convertAndSend(DESTINATION + chatReq.getChatRoomNo() + chatReq, msg);
    }


    @MessageMapping("/chat")
    public void sentChat(@Payload ChatCreateReq chatReq) {
        ChatRes chatRes = chatService.createChat(chatReq);
        template.convertAndSend(DESTINATION + chatReq.getChatRoomNo(), chatRes);
    }

    @EventListener
    public void handleSessionConnected(SessionConnectedEvent event) {
        String simpSessionId = (String) event.getMessage().getHeaders().get("simpSessionId");

        if (event.getUser() != null) {
            Optional<Principal> user = Optional.ofNullable(event.getUser());
            if (user.isPresent()) {
                try {
                    String username = user.get().getName();
                    log.info("username: {}", username);
                } catch (Exception e) {
                    throw new MessageDeliveryException("인증 정보가 올바르지 않습니다. 다시 로그인 후 이용해주세요.");
                }
            }
        }
    }

    @EventListener
    public void handleSessionSubscribe(SessionSubscribeEvent event) {
        String destination = (String) event.getMessage().getHeaders().get("simpDestination");
        assert destination != null;
        Optional<Principal> user = Optional.ofNullable(event.getUser());
        if (destination.equals(DESTINATION) && user.isPresent()) {
            String msg = user.get().getName() + "님이 채팅방에 입장하였습니다.";
            template.convertAndSend(destination, msg);
        }
    }

    @EventListener
    public void handleSessionSubscribe(SessionUnsubscribeEvent event) {
        String destination = (String) event.getMessage().getHeaders().get("simpDestination");
        assert destination != null;
        Optional<Principal> user = Optional.ofNullable(event.getUser());
        if (destination.equals(DESTINATION) && user.isPresent()) {
            String msg = user.get().getName() + "님이 채팅방에서 퇴장하였습니다.";
            template.convertAndSend(destination, msg);
        }
    }

}

