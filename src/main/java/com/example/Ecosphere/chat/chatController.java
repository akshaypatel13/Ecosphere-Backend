package com.example.Ecosphere.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class chatController implements WebSocketConfigurer {

    private final static String CHAT_ENDPOINT = "/chat";

    /*
    The interface WebSocketConfigurer allows us to register web sockets received from
    the front-End application, set origins, and define endpoints for the resource availability.
    Thus, In this case we are allowing web sockets from all cross-origins which needs to be secured
    in future implementations based on the registered events. This class can be considered as connection
    establishment class for the web sockets and further working will be done by chatService class.
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler((WebSocketHandler) getChatWebSocketHandler(), CHAT_ENDPOINT)
                .setAllowedOrigins("*");
    }

    @Bean
    public chatService getChatWebSocketHandler(){
        return new chatService();
    }
}
