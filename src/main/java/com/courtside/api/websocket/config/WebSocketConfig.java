package com.courtside.api.websocket.config;

import com.courtside.api.websocket.handler.OddsWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final OddsWebSocketHandler oddsWebSocketHandler;

    @Autowired
    public WebSocketConfig(OddsWebSocketHandler oddsWebSocketHandler) {
        this.oddsWebSocketHandler = oddsWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(oddsWebSocketHandler, "/ws/odds")
                .setAllowedOrigins("http://localhost:3001") // Specific to your React port
                .setAllowedOrigins("https://nba-courtside.vercel.app/")
                .withSockJS();
    }


}