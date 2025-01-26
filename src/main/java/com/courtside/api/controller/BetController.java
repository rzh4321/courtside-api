package com.courtside.api.controller;

import com.courtside.api.dto.BetDTO;
import com.courtside.api.dto.PlaceBetRequest;
import com.courtside.api.service.BetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bets")
@RequiredArgsConstructor
public class BetController {
    private final BetService betService;

    @GetMapping
    public ResponseEntity<List<BetDTO>> getCurrentUserBets() {
        return ResponseEntity.ok(betService.getCurrentUserBets());
    }

    @PostMapping
    public ResponseEntity<BetDTO> placeBet(@RequestBody PlaceBetRequest request) {
        return ResponseEntity.ok(betService.placeBet(request));
    }
}