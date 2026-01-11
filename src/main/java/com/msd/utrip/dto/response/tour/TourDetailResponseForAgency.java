package com.msd.utrip.dto.response.tour;

import com.msd.utrip.dto.response.file.ImageDto;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record TourDetailResponseForAgency(
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
    List<ImageDto> images,
    List<ScheduleDto> schedules,
    List<InclusionDto> inclusions) {}
