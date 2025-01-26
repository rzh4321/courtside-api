package com.courtside.api.repository;

import com.courtside.api.entity.Bet;
import com.courtside.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> findByUserId(Long userId);
    List<Bet> findByUserOrderByPlacedAtDesc(User user);

}