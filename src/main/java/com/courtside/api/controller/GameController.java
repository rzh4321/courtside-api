package com.courtside.api.controller;

import com.courtside.api.dto.GameDTO;
import com.courtside.api.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/games")
@AllArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameDTO>> getAllGames() {
        List<GameDTO> games = gameService.getAllGames();
        return ResponseEntity.ok(games);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<GameDTO>> getGamesByDate(@PathVariable LocalDate date) {
        List<GameDTO> games = gameService.getGamesByDate(date);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable String gameId) {
        GameDTO game = gameService.getGameById(gameId);
        return ResponseEntity.ok(game);
    }
}