package com.courtside.api.service;

import com.courtside.api.dto.BetDTO;
import com.courtside.api.dto.PlaceBetRequest;

import java.util.List;

public interface BetService {
    List<BetDTO> getCurrentUserBets();
    BetDTO placeBet(PlaceBetRequest request);
}