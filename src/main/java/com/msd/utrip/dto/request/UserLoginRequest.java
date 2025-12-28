package com.msd.utrip.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(@NotBlank String phone, @NotBlank String password) {}
