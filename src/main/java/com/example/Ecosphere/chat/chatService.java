package com.example.Ecosphere.chat;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class chatService extends TextWebSocketHandler {

    private final List<WebSocketSession> webSocketSessions = new ArrayList<>();
    private HashSet<TextMessage> Messages = new HashSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessions.add(session);
        if(webSocketSessions.contains(session)){
            for(TextMessage message : Messages){
                session.sendMessage(message);
            }
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for(WebSocketSession webSocketSession : webSocketSessions){
            this.Messages.add(message);
            webSocketSession.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessions.remove(session);
    }
}
