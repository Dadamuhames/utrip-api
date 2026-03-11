package com.msd.utrip.dto.request.admin;

import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record CountryRequest(@NotNull Map<String, String> name) {}
