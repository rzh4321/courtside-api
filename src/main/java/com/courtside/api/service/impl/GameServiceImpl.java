package com.courtside.api.service.impl;

import com.courtside.api.dto.GameDTO;
import com.courtside.api.entity.Game;
import com.courtside.api.exception.ResourceNotFoundException;
import com.courtside.api.repository.GameRepository;
import com.courtside.api.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<GameDTO> getAllGames() {

        List<Game> games = gameRepository.findAll();

        List<GameDTO> dtos = games.stream()
                .map(game -> {
                    GameDTO dto = modelMapper.map(game, GameDTO.class);
                    return dto;
                })
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    public List<GameDTO> getGamesByDate(LocalDate date) {
        List<Game> games = gameRepository.findByGameDate(date);
        return games.stream()
                .map(game -> modelMapper.map(game, GameDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public GameDTO getGameById(String gameId) {
        Game game = gameRepository.findByGameId(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id: " + gameId));
        return modelMapper.map(game, GameDTO.class);
    }
}