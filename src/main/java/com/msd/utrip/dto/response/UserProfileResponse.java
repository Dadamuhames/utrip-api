package com.msd.utrip.dto.response;

public record UserProfileResponse(
    Long id, String fullName, String image, String phone, String email) {}
