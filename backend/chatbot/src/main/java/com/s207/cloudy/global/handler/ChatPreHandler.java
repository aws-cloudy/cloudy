//package com.s207.cloudy.global.handler;
//
//import com.pawsitive.auth.Role;
//import com.pawsitive.auth.jwt.JwtTokenProvider;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.UnsupportedJwtException;
//import java.util.List;
//import java.util.Objects;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.MessageDeliveryException;
//import org.springframework.messaging.simp.stomp.StompCommand;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.messaging.support.ChannelInterceptor;
//import org.springframework.messaging.support.MessageHeaderAccessor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.util.StringUtils;
//
///**
// * @author 이하늬
// * @since 1.0
// */
//@Configuration
//@RequiredArgsConstructor
//@Order(Ordered.HIGHEST_PRECEDENCE + 99)
//@Slf4j
//public class ChatPreHandler implements ChannelInterceptor {
//
//    //    private final JwtUtil jwtUtil;
//    JwtTokenProvider jwtTokenProvider;
//    Long memberId = 0L;
//
//    @Override
//    public Message<?> preSend(Message<?> message, MessageChannel channel) {
//        try {
//            StompHeaderAccessor headerAccessor =
//                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//
//            String token =
//                Objects.requireNonNull(headerAccessor.getNativeHeader("Authorization")).get(0);
//            if (!StringUtils.hasText(token)) {
//                log.info("chat header가 없는 요청입니다.");
//                throw new MalformedJwtException("jwt");
//            }
//
//            StompCommand command = headerAccessor.getCommand();
//
//            if (StompCommand.UNSUBSCRIBE.equals(command) || StompCommand.MESSAGE.equals(command) ||
//                StompCommand.CONNECTED.equals(command) || StompCommand.SEND.equals(command)) {
//                return message;
//            }
//
//            if (StompCommand.ERROR.equals(command)) {
//                throw new MessageDeliveryException("전송에 실패하였습니다.");
//            }
//
//
//            if (token.startsWith("Bearer ")) {
//                token = token.substring(7);
//            } else {
//                log.error("Authorization 헤더 형식이 틀립니다. : {}", token);
//                throw new MalformedJwtException("jwt");
//            }
//
//            // TODO [Yi] JWT로 멤버 id 받아오기 & 유효한 JWT인지 체크하는 로직
//            try {
//                jwtTokenProvider.validateToken(token);
//                Authentication user = jwtTokenProvider.getAuthentication(token);
//                headerAccessor.setUser(user);
//            } catch (MessageDeliveryException e) {
//                throw new MessageDeliveryException("메세지 에러");
//            } catch (SecurityException | MalformedJwtException | ExpiredJwtException |
//                     UnsupportedJwtException |
//                     IllegalArgumentException e) {
//                throw new MessageDeliveryException("인증 정보가 올바르지 않습니다. 다시 로그인 후 이용해주세요.");
//            }
//        } catch (MessageDeliveryException e) {
//            log.error("메시지 에러");
//            throw new MessageDeliveryException("error");
//        }
//        return message;
//    }
//
//    private void setAuthentication(Message<?> message, StompHeaderAccessor headerAccessor) {
//        UsernamePasswordAuthenticationToken
//            authentication = new UsernamePasswordAuthenticationToken(memberId, null,
//            List.of(new SimpleGrantedAuthority(Role.USER.getKey())));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        headerAccessor.setUser(authentication);
//    }
//}