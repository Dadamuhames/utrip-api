package com.msd.utrip.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TourFilterRequest(
    String search,
    LocalDate dateFrom,
    LocalDate dateTo,
    BigDecimal priceFrom,
    BigDecimal priceTo,
    Long agencyId,
    Long categoryId,
    Long regionId) {}
