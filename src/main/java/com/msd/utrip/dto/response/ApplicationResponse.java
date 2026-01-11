package com.msd.utrip.dto.response;

import com.msd.utrip.dto.response.tour.TourSimpleResponse;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public record ApplicationResponse(
    Long id,
    String fullName,
    String phone,
    Integer personCount,
    TourSimpleResponse tour,
    OffsetDateTime createdAt) {}
