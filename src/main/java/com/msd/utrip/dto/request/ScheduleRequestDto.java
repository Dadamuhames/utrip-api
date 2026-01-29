package com.msd.utrip.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record ScheduleRequestDto(
    @NotNull Integer dayNumber,
    @NotBlank Map<String, String> title,
    @NotBlank Map<String, String> subtitle) {}
