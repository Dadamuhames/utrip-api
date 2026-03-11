package com.msd.utrip.dto.response.agency;

import com.msd.utrip.dto.response.file.ImageDto;

import java.util.List;

public record AgencyProfileDetailResponse(
    Long id,
    String name,
    String image,
    String subtitle,
    String info,
    String address,
    String phone,
    String email,
    List<ImageDto> images,
    List<ImageDto> videos,
    Integer activeTripsCount,
    Integer finishedTripsCount,
    Integer totalParticipantsCount) {}
