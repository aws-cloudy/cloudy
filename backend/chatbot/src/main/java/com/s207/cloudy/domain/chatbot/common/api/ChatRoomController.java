package com.s207.cloudy.domain.chatbot.common.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.s207.cloudy.domain.chatbot.common.application.ChatRoomService;
import com.s207.cloudy.domain.chatbot.common.dto.ChatRoomCreateReq;
import com.s207.cloudy.domain.chatbot.common.dto.ChatRoomDetailRes;
import com.s207.cloudy.domain.chatbot.common.dto.ChatRoomListRes;
import com.s207.cloudy.domain.chatbot.common.dto.ChatRoomRes;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chatrooms")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;


    @PostMapping
    public ResponseEntity<ChatRoomRes> createRoom(
        @RequestBody ChatRoomCreateReq chatRoomCreateReq, Authentication authentication) {
        return ResponseEntity
            .status(CREATED)
            .body(chatRoomService.createChatRoom(chatRoomCreateReq, authentication));
    }

    @GetMapping
    public ResponseEntity<List<ChatRoomListRes>> getChatRoomListByUserNo(@RequestParam String type,
                                                                         @RequestParam int value) {
        return ResponseEntity
            .status(OK)
            .body(chatRoomService.getChatRoomList(type, value));
    }

    @GetMapping("/{chatRoomNo}")
    public ResponseEntity<ChatRoomDetailRes> getChatRoomByChatRoomNo(@PathVariable int chatRoomNo) {
        return ResponseEntity
            .status(OK)
            .body(chatRoomService.getChatRoomDetail(chatRoomNo));
    }




}
