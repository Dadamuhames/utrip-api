package com.msd.utrip.dto.response.admin;

import java.time.LocalDateTime;
import java.util.Map;

public record CategoryAdminResponse(
    Long id, Map<String, String> title, String image, LocalDateTime createdAt) {}
