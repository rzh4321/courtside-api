package com.courtside.api.service.impl;

import com.courtside.api.dto.GameDTO;
import com.courtside.api.entity.Game;
import com.courtside.api.repository.GameRepository;
import com.courtside.api.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GameDTO> getAllGames() {
        System.out.println("hi");

        List<Game> games = gameRepository.findAll();

        // Debug: Print first game entity
        if (!games.isEmpty()) {
            Game firstGame = games.get(0);
            System.out.println("first game: " + firstGame.getId());
//            System.out.println("HomeTeam: " + firstGame.getHomeTeam());
//            System.out.println("AwayTeam: " + firstGame.getAwayTeam());
//            System.out.println("HomeSpreadOdds: " + firstGame.getHomeSpreadOdds());
            // ... print other fields
        }

        List<GameDTO> dtos = games.stream()
                .map(game -> {
                    GameDTO dto = modelMapper.map(game, GameDTO.class);
                    // Debug: Print first mapped DTO
                    if (game.equals(games.get(0))) {
//                        System.out.println("ID: " + dto.getId());
//                        System.out.println("GameID: " + dto.getGameId());
//                        System.out.println("HomeTeam: " + dto.getHomeTeam());
//                        System.out.println("AwayTeam: " + dto.getAwayTeam());
//                        System.out.println("HomeSpreadOdds: " + dto.getHomeSpreadOdds());
                        // ... print other fields
                    }
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
}