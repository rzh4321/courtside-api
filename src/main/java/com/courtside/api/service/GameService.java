package com.courtside.api.service;

import com.courtside.api.dto.GameDTO;

import java.time.LocalDate;
import java.util.List;

public interface GameService {
    public List<GameDTO> getAllGames();
    public List<GameDTO> getGamesByDate(LocalDate date);
    GameDTO getGameById(String gameId);
    GameDTO getGameByTeamsAndDate(String homeTeam, String awayTeam, LocalDate gameDate);
    GameDTO updateGameId(String homeTeam, String awayTeam, LocalDate gameDate, String gameId);
}
