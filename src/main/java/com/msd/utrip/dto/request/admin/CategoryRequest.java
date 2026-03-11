package com.msd.utrip.dto.request.admin;

import jakarta.validation.constraints.NotNull;
import java.util.Map;

public record CategoryRequest(@NotNull Map<String, String> title, String image) {}
