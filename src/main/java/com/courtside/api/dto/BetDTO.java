package com.courtside.api.dto;

import com.courtside.api.entity.BetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BetDTO {
    private Long id;
    private Long userId;
    private String gameId;
    private BetType betType;
    private BigDecimal odds;
    private BigDecimal amountPlaced;
    private BigDecimal totalPayout;
    private BigDecimal selectedLine;
    private LocalDateTime placedAt;
    private String status;

    // Add some game details for convenience
    private String homeTeam;
    private String awayTeam;
    private LocalDateTime gameTime;
}