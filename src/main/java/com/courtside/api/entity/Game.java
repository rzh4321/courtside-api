package com.courtside.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.OffsetDateTime;
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

    @Column(name = "created_at", columnDefinition = "timestamptz", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamptz", nullable = false)
    private OffsetDateTime updatedAt;

    @Column(nullable = false)
    private LocalDate gameDate;

    @OneToMany(mappedBy = "game")
    private List<Bet> bets;
}