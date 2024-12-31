package com.courtside.api.service;

import com.courtside.api.dto.GameDTO;

import java.time.LocalDate;
import java.util.List;

public interface GameService {
    public List<GameDTO> getAllGames();
    public List<GameDTO> getGamesByDate(LocalDate date);
}
