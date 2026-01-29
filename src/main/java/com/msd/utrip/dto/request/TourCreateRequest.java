package com.msd.utrip.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record TourCreateRequest(
    @NotNull Map<String, String> title,
    @NotNull Map<String, String> subtitle,
    @NotNull Map<String, String> info,
    @NotNull Map<String, String> additionalInfo,
    @NotBlank String address,
    String image,
    @NotNull Integer maxPeople,
    @NotNull BigDecimal price,
    @NotNull LocalDate startDate,
    @NotNull LocalDate endDate,
    @NotNull Long categoryId,
    @NotNull Long regionId,
    List<String> images,
    @NotNull List<ScheduleRequestDto> schedule,
    @NotNull List<InclusionRequestDto> inclusions) {}
