package com.msd.utrip.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AgencyLoginRequest(@NotBlank String login, @NotBlank String password) {}
