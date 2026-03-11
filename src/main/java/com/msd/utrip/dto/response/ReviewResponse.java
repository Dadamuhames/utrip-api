package com.msd.utrip.dto.response;

import java.time.LocalDateTime;

public record ReviewResponse(
    Long id, UserResponse user, String review, Integer rating, LocalDateTime createdAt) {}
