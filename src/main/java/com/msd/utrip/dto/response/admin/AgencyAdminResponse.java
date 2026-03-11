package com.msd.utrip.dto.response.admin;

import java.time.LocalDateTime;

public record AgencyAdminResponse(Long id, String name, String login, String image, LocalDateTime createdAt) {}
