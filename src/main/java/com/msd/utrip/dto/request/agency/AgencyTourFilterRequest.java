package com.msd.utrip.dto.request.agency;


public record AgencyTourFilterRequest(String search, Long categoryId, boolean isActive) {}
