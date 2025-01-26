package com.courtside.api.dto;

import com.courtside.api.entity.BetType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlaceBetRequest {
    private String gameId;
    private BetType betType;
    private BigDecimal amountToPlace;
    private BigDecimal selectedLine;  // For spread/total bets
}