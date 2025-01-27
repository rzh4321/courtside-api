package com.courtside.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameIdUpdateRequest {
    private String homeTeam;
    private String awayTeam;
    private String gameId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gameDate;
}