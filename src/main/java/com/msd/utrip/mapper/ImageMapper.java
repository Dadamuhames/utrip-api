package com.msd.utrip.mapper;

import com.msd.utrip.dto.response.file.ImageDto;
import com.msd.utrip.entity.agency.AgencyImageEntity;
import com.msd.utrip.entity.agency.AgencyVideoEntity;
import com.msd.utrip.entity.tour.TourImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ImageMapper {

  public abstract ImageDto mapTourImage(TourImageEntity tourImage);

  public abstract ImageDto mapAgencyImage(AgencyImageEntity image);

  public abstract ImageDto mapAgencyVideo(AgencyVideoEntity image);
}
