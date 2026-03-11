package com.msd.utrip.dto.response.admin;

import java.time.LocalDateTime;
import java.util.Map;

public record CountryAdminResponse(Long id, Map<String, String> name, LocalDateTime createdAt) {}
