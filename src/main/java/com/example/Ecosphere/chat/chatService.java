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

    /*
    After the successful webSocketRegistration, the particular users are added to the webSocketSession
    that intends to broadcast the messages to the every single user. The TextWebSocketHandler allows us to
    broadcast the message to the discussion channel using three Override methods:
    1. afterConnectionEstablished - this function will add users to the session and will also send previous
    messages of the discussion forum when the user was not enrolled in.
    2. handleTextMessage - this function performs the actual communication of the chat messages to the
    frontEnd part and will also store the messages that can be useful to the new users.
    3. afterConnectionClosed - this function will remove users from the session so that they don't receive
    any further chat messages.
     */

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
