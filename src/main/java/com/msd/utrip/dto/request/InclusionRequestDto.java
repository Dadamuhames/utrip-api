package com.msd.utrip.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.Map;

public record InclusionRequestDto(@NotBlank Map<String, String> title, boolean isIncluded) {}
