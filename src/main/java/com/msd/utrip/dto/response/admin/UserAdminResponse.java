package com.msd.utrip.dto.response.admin;

import java.time.LocalDateTime;

public record UserAdminResponse(
    Long id,
    String fullName,
    String phone,
    String image,
    boolean isActive,
    LocalDateTime createdAt) {}
