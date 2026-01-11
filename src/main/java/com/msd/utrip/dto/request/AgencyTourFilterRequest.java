package com.msd.utrip.dto.request;


public record AgencyTourFilterRequest(String search, Long categoryId, boolean isActive) {}
