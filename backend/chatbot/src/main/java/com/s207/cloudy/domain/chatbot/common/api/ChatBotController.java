package com.s207.cloudy.domain.chatbot.common.api;

import com.s207.cloudy.domain.chatbot.common.application.ChatBotService;
import com.s207.cloudy.domain.chatbot.common.dto.ChatListRes;
import com.s207.cloudy.domain.chatbot.common.dto.QuestionReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/chats")
public class ChatBotController {
    private final ChatBotService chatBotService;

    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generateChat(@RequestBody QuestionReq question) {
        log.info("컨트롤러 입력");
        return chatBotService.question(question);
    }

    @GetMapping
    public ResponseEntity<ChatListRes> getChatList(@RequestParam int type) {
        return ResponseEntity
                .status(OK)
                .body(chatBotService.getChatList(type));
    }


//    @PostMapping(value = "chatbot2")
//    public ResponseEntity<String> eturnChat(@RequestBody QuestionReq question) {
//        log.info("컨트롤러 입력");
//        return ResponseEntity
//                .status(OK)
//                .body(qnaService.generateChat2(question));
//    }
}
