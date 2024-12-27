package com.courtside.api.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/db")
    public ResponseEntity<String> testConnection() {
        try {
            // This will throw an exception if connection fails
            return ResponseEntity.ok("Database connection successful!");
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Database connection failed: " + e.getMessage());
        }
    }
}