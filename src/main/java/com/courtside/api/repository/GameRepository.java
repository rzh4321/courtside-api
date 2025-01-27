package com.courtside.api.repository;

import com.courtside.api.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByGameDate(LocalDate gameDate);
    Optional<Game> findByGameId(String gameId);
    Optional<Game> findByHomeTeamAndAwayTeamAndGameDate(String homeTeam, String awayTeam, LocalDate gameDate);

}