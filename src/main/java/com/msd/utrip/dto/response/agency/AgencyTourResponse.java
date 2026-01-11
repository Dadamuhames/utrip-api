package com.msd.utrip.dto.response.agency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public record AgencyTourResponse(
    Long id,
    String title,
    String address,
    String image,
    BigDecimal price,
    LocalDate startDate,
    LocalDate endDate,
    Integer applicationCount,
    OffsetDateTime createdAt) {}
