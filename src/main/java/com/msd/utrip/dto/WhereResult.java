package com.msd.utrip.dto;

import java.util.Map;

public record WhereResult(String whereClause, Map<String, Object> params) {}


