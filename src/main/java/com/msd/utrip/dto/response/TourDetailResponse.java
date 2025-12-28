package com.msd.utrip.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record TourDetailResponse(
    Long id,
    String title,
    String address,
    LocalDate dateFrom,
    LocalDate dateTo,
    Double rating,
    Integer reviewCount,
    Integer maxPlaces,
    Integer placesLeft,
    BigDecimal price,
    String info,
    String additionalInfo,
    AgencyResponse agency,
    List<ImageDto> images,
    List<ScheduleDto> schedules,
    List<InclusionDto> inclusions) {}
