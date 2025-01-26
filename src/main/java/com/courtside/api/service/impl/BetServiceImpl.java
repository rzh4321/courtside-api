package com.courtside.api.service.impl;

import com.courtside.api.dto.BetDTO;
import com.courtside.api.dto.PlaceBetRequest;
import com.courtside.api.entity.Bet;
import com.courtside.api.entity.Game;
import com.courtside.api.entity.User;
import com.courtside.api.repository.BetRepository;
import com.courtside.api.repository.GameRepository;
import com.courtside.api.repository.UserRepository;
import com.courtside.api.service.BetService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BetServiceImpl implements BetService {
    private final BetRepository betRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public List<BetDTO> getCurrentUserBets() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return betRepository.findByUserOrderByPlacedAtDesc(user)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public BetDTO placeBet(PlaceBetRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Game game = gameRepository.findByGameId(request.getGameId())
                .orElseThrow(() -> new RuntimeException("Game not found"));

        // Add validation logic here (valid bet type, etc.)
        validateBet(game, request);

        Bet bet = new Bet();
        bet.setUser(user);
        bet.setGame(game);
        bet.setBetType(request.getBetType());
        bet.setAmountPlaced(request.getAmountToPlace());
        bet.setSelectedLine(request.getSelectedLine());
        bet.setPlacedAt(LocalDateTime.now());
        bet.setStatus("PENDING");

        // Calculate odds and payout based on bet type and selected line
        calculateOddsAndPayout(bet);

        // Update user statistics
        user.setAmountPlaced(user.getAmountPlaced() + request.getAmountToPlace().longValue());
        user.setBetsPlaced(user.getBetsPlaced() + 1);

        betRepository.save(bet);
        userRepository.save(user);

        return convertToDTO(bet);
    }

    private void validateBet(Game game, PlaceBetRequest request) {

        // Add more validation as needed
    }

    private void calculateOddsAndPayout(Bet bet) {
        BigDecimal amountPlaced = bet.getAmountPlaced();
        BigDecimal selectedLine = bet.getSelectedLine();

        // Convert American odds to decimal odds
        BigDecimal decimalOdds;
        if (selectedLine.compareTo(BigDecimal.ZERO) > 0) {
            // Positive odds (e.g., +150)
            decimalOdds = selectedLine.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)
                    .add(BigDecimal.ONE);
        } else {
            // Negative odds (e.g., -110)
            decimalOdds = BigDecimal.valueOf(100)
                    .divide(selectedLine.abs(), 4, RoundingMode.HALF_UP)
                    .add(BigDecimal.ONE);
        }

        // Calculate total payout (stake * decimal odds)
        BigDecimal totalPayout = amountPlaced.multiply(decimalOdds)
                .setScale(2, RoundingMode.HALF_UP);

        bet.setOdds(selectedLine);
        bet.setTotalPayout(totalPayout);
    }

    private BetDTO convertToDTO(Bet bet) {
        return BetDTO.builder()
                .id(bet.getId())
                .userId(bet.getUser().getId())
                .gameId(bet.getGame().getGameId())
                .betType(bet.getBetType())
                .odds(bet.getOdds())
                .amountPlaced(bet.getAmountPlaced())
                .totalPayout(bet.getTotalPayout())
                .selectedLine(bet.getSelectedLine())
                .placedAt(bet.getPlacedAt())
                .status(bet.getStatus())
                .homeTeam(bet.getGame().getHomeTeam())
                .awayTeam(bet.getGame().getAwayTeam())
                .build();
    }
}