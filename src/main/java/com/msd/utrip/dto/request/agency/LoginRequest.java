package com.msd.utrip.dto.request.agency;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank String login, @NotBlank String password) {}
