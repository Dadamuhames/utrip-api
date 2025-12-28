package com.msd.utrip.dto.request;

import com.msd.utrip.validation.PhoneNumberConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ApplicationRequest(
    @NotBlank String name,
    @PhoneNumberConstraint String phone,
    @NotBlank String email,
    @NotNull Integer personCount) {}
