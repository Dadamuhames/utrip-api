package com.msd.utrip.mapper;

import com.msd.utrip.dto.response.ImageDto;
import com.msd.utrip.entity.AgencyImageEntity;
import com.msd.utrip.entity.TourImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ImageMapper {

  public abstract ImageDto mapTourImage(TourImageEntity tourImage);

  public abstract ImageDto mapAgencyImage(AgencyImageEntity image);
}
