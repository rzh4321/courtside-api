package com.courtside.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gameId;

    @Column(nullable = false)
    private String homeTeam;

    @Column(nullable = false)
    private String awayTeam;

    private BigDecimal homeSpreadOdds;
    private BigDecimal awaySpreadOdds;
    private BigDecimal homeSpread;

    @Column(nullable = false)
    private BigDecimal openingHomeSpread;
    private BigDecimal homeMoneyline;
    private BigDecimal awayMoneyline;

    @Column(nullable = false)
    private BigDecimal openingOverUnder;
    private BigDecimal overUnder;
    private BigDecimal overOdds;
    private BigDecimal underOdds;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/New_York")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/New_York")
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/New_York")
    private LocalDate gameDate;

    @OneToMany(mappedBy = "game")
    private List<Bet> bets;
}

