package com.msd.utrip.dto.response;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Map;

public record AgencyProfileResponse(
    Long id,
    String name,
    String image,
    String phone,
    String email,
    String address,
    Map<String, String> subtitle,
    Map<String, String> info,
   OffsetDateTime createdAt) {}
