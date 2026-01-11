package com.msd.utrip.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewRequest(
    @NotNull Long agencyId, @NotNull Integer rating, @NotBlank String review) {}
