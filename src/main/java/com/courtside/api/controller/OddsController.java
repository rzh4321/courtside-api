package com.courtside.api.controller;

import com.courtside.api.dto.GameDTO;
import com.courtside.api.service.GameService;
import com.courtside.api.websocket.handler.OddsWebSocketHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OddsController {

    private final OddsWebSocketHandler webSocketHandler;
    private final GameService gameService;

    @Autowired
    public OddsController(OddsWebSocketHandler webSocketHandler, GameService gameService) {
        this.webSocketHandler = webSocketHandler;
        this.gameService = gameService;
    }


    // Original endpoint for game_id
//    @PostMapping("/notify-odds-update")
//    public ResponseEntity<Void> notifyOddsUpdate(@RequestBody Map<String, String> payload) {
//        String gameId = payload.get("gameId");
//        System.out.println("IN NOTIFYODDS UPDATE BY GAMEID: "+ gameId);
//        if (gameId != null) {
//            System.out.println("GAMEID IS NOT NULL, BROADCASTING GAMEID TO REACT");
//            webSocketHandler.broadcastOddsUpdate(gameId);
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.badRequest().build();
//    }
    // Original endpoint for game_id
    @PostMapping("/notify-odds-update")
    public ResponseEntity<Void> notifyOddsUpdate(@RequestBody Map<String, String> payload) {
        String gameId = payload.get("gameId");
        System.out.println("IN NOTIFYODDS UPDATE BY GAMEID: "+ gameId);
        if (gameId != null) {
            System.out.println("GAMEID IS NOT NULL, BROADCASTING DATA TO REACT");
            GameDTO gameData = gameService.getGameById(gameId);
            webSocketHandler.broadcastOddsUpdate(gameId, gameData);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    // New endpoint for team-based identification
//    @PostMapping("/notify-odds-update-by-teams")
//    public ResponseEntity<Void> notifyOddsUpdateByTeams(@RequestBody Map<String, String> payload) {
//        String homeTeam = payload.get("homeTeam");
//        String awayTeam = payload.get("awayTeam");
//        String gameDateStr = payload.get("gameDate");
//        System.out.println("IN NOTIFY ODDS BY TEAMS: " + awayTeam + " AT " + homeTeam + " GAMEDATE: " + gameDateStr);
//
//
//        if (homeTeam != null && awayTeam != null && gameDateStr != null) {
//            System.out.println("TEAMS AND GAME DATE NOT NULL, BROADCASTING TEAMS TO REACT");
//
//            webSocketHandler.broadcastOddsUpdateByTeams(homeTeam, awayTeam, gameDateStr);
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.badRequest().build();
//    }

    @PostMapping("/notify-odds-update-by-teams")
    public ResponseEntity<Void> notifyOddsUpdateByTeams(@RequestBody Map<String, String> payload) {
        String homeTeam = payload.get("homeTeam");
        String awayTeam = payload.get("awayTeam");
        String gameDateStr = payload.get("gameDate");
        System.out.println("IN NOTIFY ODDS BY TEAMS: " + awayTeam + " AT " + homeTeam + " GAMEDATE: " + gameDateStr);

        if (homeTeam != null && awayTeam != null && gameDateStr != null) {
            System.out.println("TEAMS AND GAME DATE NOT NULL, BROADCASTING TEAMS TO REACT");
            LocalDate gameDate = LocalDate.parse(gameDateStr);
            GameDTO gameData = gameService.getGameByTeamsAndDate(homeTeam, awayTeam, gameDate);
            webSocketHandler.broadcastOddsUpdateByTeams(homeTeam, awayTeam, gameDateStr, gameData);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}