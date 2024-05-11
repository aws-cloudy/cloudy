package com.s207.cloudy.domain.chatbot.common.api;

import com.s207.cloudy.domain.chatbot.common.application.ChatBotService;
import com.s207.cloudy.domain.chatbot.common.dto.ChatListRes;
import com.s207.cloudy.domain.chatbot.common.dto.ChatReq;
import com.s207.cloudy.domain.members.entity.Member;
import com.s207.cloudy.global.infra.modertation.ModerationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/chats")
public class ChatBotController {
    private final ChatBotService chatBotService;
    private final ModerationService moderationService;

    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generateChat(@RequestBody ChatReq question, @AuthenticationPrincipal Member member) {
        log.info("{}", question);
        return chatBotService.question(question, member.getId());
    }

    @GetMapping
    public ResponseEntity<ChatListRes> getChatList(@RequestParam int type, @AuthenticationPrincipal Member member) {
        return ResponseEntity
                .status(OK)
                .body(chatBotService.getChatList(type, member.getId()));
    }

}
