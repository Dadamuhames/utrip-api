package com.msd.utrip.dto.response;

import java.time.OffsetDateTime;

public record ReviewResponse(
    Long id, UserResponse user, String review, Integer rating, OffsetDateTime createdAt) {}
