package com.courtside.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private Long amountDeposited = 0L;
    private Long amountPlaced = 0L;
    private Long amountWon = 0L;
    private Long betsPlaced = 0L;
    private Long betsWon = 0L;

    @OneToMany(mappedBy = "user")
    private List<Bet> bets;
}