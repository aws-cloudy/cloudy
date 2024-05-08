package com.s207.cloudy.global.config;

import com.s207.cloudy.global.handler.ChatErrorHandler;
import com.s207.cloudy.global.handler.StompHandshakeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    //    private final ChatPreHandler chatPreHandler;
    private final ChatErrorHandler chatErrorHandler;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //클라이언트에서 보낸 메세지를 받을 prefix
        registry.setApplicationDestinationPrefixes("/api/v1/chats/pub"); // 보내기
        //해당 주소를 구독하고 있는 클라이언트들에게 메세지 전달
        registry.enableSimpleBroker("/api/v1/chats/sub"); //받기
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat")   //SockJS 연결 주소
            .addInterceptors(new StompHandshakeInterceptor())
            .setAllowedOrigins(
                "http://localhost:3000", "http://localhost:8080")
            .setAllowedOriginPatterns(
                "http://localhost:3000/**", "http://localhost:8080/**")
            .withSockJS()
            .setDisconnectDelay(30 * 1000)
            .setClientLibraryUrl(
                "https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.4/sockjs.min.js");
//        registry.setErrorHandler(chatErrorHandler);
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(chatPreHandler);
    }
}
