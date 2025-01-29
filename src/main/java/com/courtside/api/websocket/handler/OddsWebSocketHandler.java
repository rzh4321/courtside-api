package com.courtside.api.websocket.handler;

import com.courtside.api.dto.GameDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OddsWebSocketHandler extends TextWebSocketHandler {
    private static final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
    private final ObjectMapper objectMapper;

    public OddsWebSocketHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

//    public void broadcastOddsUpdate(String gameId) {
//        String message = String.format("{\"type\":\"ODDS_UPDATE\",\"gameId\":\"%s\"}", gameId);
//        sessions.forEach(session -> {
//            try {
//                if (session.isOpen()) {
//                    session.sendMessage(new TextMessage(message));
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }

    public void broadcastOddsUpdate(String gameId, GameDTO gameData) {
        Map<String, Object> payload = Map.of(
                "type", "ODDS_UPDATE",
                "gameId", gameId,
                "data", gameData
        );
        broadcastMessage(payload);
    }

//    public void broadcastOddsUpdateByTeams(String homeTeam, String awayTeam, String gameDate) {
//        String message = String.format(
//                "{\"type\":\"ODDS_UPDATE_BY_TEAMS\",\"homeTeam\":\"%s\",\"awayTeam\":\"%s\",\"gameDate\":\"%s\"}",
//                homeTeam, awayTeam, gameDate
//        );
//        broadcastMessage(message);
//    }

    public void broadcastOddsUpdateByTeams(String homeTeam, String awayTeam, String gameDate, GameDTO gameData) {
        Map<String, Object> payload = Map.of(
                "type", "ODDS_UPDATE_BY_TEAMS",
                "homeTeam", homeTeam,
                "awayTeam", awayTeam,
                "gameDate", gameDate,
                "data", gameData
        );
        broadcastMessage(payload);
    }

//    private void broadcastMessage(String message) {
//        sessions.forEach(session -> {
//            try {
//                if (session.isOpen()) {
//                    session.sendMessage(new TextMessage(message));
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }

    private void broadcastMessage(Map<String, Object> payload) {
        try {
            String message = objectMapper.writeValueAsString(payload);
            TextMessage textMessage = new TextMessage(message);

            sessions.forEach(session -> {
                try {
                    if (session.isOpen()) {
                        session.sendMessage(textMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}