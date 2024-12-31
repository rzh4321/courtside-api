package com.courtside.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {
    private Long id;
    private String gameId;
    private String homeTeam;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gameDate;
}