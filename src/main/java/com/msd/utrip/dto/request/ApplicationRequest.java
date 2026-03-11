package com.msd.utrip.dto.request;

import com.msd.utrip.validation.PhoneNumberConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ApplicationRequest(
    @NotBlank String name,
    @PhoneNumberConstraint String phone,
    @NotBlank String email,
    @NotNull @Positive Integer personCount) {}
