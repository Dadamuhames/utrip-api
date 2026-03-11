package com.msd.utrip.dto.response.admin;

import java.time.LocalDateTime;
import java.util.Map;

public record RegionAdminResponse(Long id, Map<String, String> name, CountryAdminResponse country, LocalDateTime createdAt) {}
