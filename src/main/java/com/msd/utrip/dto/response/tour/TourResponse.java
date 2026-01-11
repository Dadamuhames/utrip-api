package com.msd.utrip.dto.response.tour;

import com.msd.utrip.dto.response.agency.AgencyResponse;

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
