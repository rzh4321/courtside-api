package com.courtside.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String gameId;

    @Column(nullable = false)
    private String homeTeam;

    @Column(nullable = false)
    private String awayTeam;

    private BigDecimal homeSpreadOdds;
    private BigDecimal awaySpreadOdds;
    private BigDecimal homeSpread;
    private BigDecimal openingHomeSpread;
    private BigDecimal homeMoneyline;
    private BigDecimal awayMoneyline;
    private BigDecimal openingOverUnder;
    private BigDecimal overUnder;
    private BigDecimal overOdds;
    private BigDecimal underOdds;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private LocalDate gameDate;

    @OneToMany(mappedBy = "game")
    private List<Bet> bets;
}