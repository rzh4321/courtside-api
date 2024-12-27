package com.courtside.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bets")
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BetType betType;

    @Column(nullable = false)
    private BigDecimal odds;

    @Column(nullable = false)
    private BigDecimal amountPlaced;

    @Column(nullable = false)
    private BigDecimal totalPayout;

    private BigDecimal selectedLine;

    private LocalDateTime placedAt;

    private String status = "PENDING";
}

enum BetType {
    SPREAD_HOME,
    SPREAD_AWAY,
    MONEYLINE_HOME,
    MONEYLINE_AWAY,
    OVER,
    UNDER
}