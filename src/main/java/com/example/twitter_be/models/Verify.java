package com.example.twitter_be.models;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Verify {
    private boolean status = false;
    private LocalDateTime startedAt;
    private LocalDateTime eendsAt;
    private String planType;
}
