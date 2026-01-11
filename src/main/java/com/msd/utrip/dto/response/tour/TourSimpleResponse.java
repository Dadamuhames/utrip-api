package com.msd.utrip.dto.response.tour;

import java.time.LocalDate;

public record TourSimpleResponse(Long id, String title, LocalDate startDate) {}
