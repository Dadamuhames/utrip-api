package com.msd.utrip.dto.request;

public record ApplicationFilter(String search, Long tourId, Long categoryId, boolean isActive) {}
