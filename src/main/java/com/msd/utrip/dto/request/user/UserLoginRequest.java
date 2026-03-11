package com.msd.utrip.dto.request.user;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(@NotBlank String phone, @NotBlank String password) {}
