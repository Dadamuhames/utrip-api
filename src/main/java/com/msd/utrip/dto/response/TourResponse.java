package com.msd.utrip.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public record TourResponse(
    Long id,
    String title,
    String address,
    String image,
    BigDecimal price,
    LocalDate startDate,
    LocalDate endDate,
    AgencyResponse agency,
    Double rating,
    OffsetDateTime createdAt) {}
