package com.msd.utrip.dto.request;

import com.msd.utrip.validation.PhoneNumberConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

public record AgencyProfileRequest(
    @NotBlank String name,
    @NotNull @PhoneNumberConstraint String phone,
    Map<String, String> subtitle,
    String email,
    String address,
    Map<String, String> info,
    @NotNull List<String> images,
    @NotNull List<String> videos) {}
