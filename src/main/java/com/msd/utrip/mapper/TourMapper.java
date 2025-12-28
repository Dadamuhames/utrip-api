package com.msd.utrip.mapper;

import com.msd.utrip.dto.response.ImageDto;
import com.msd.utrip.dto.response.InclusionDto;
import com.msd.utrip.dto.response.ScheduleDto;
import com.msd.utrip.dto.response.TourDetailResponse;
import com.msd.utrip.dto.response.TourResponse;
import com.msd.utrip.repository.projection.InclusionProjection;
import com.msd.utrip.repository.projection.ScheduleProjection;
import com.msd.utrip.repository.projection.TourDetailProjection;
import com.msd.utrip.repository.projection.TourProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TourMapper {

  @Mapping(target = "agency.id", expression = "java(tourProjection.getAgencyId())")
  @Mapping(target = "agency.image", expression = "java(tourProjection.getAgencyImage())")
  @Mapping(target = "agency.name", expression = "java(tourProjection.getAgencyName())")
  @Mapping(target = "agency.rating", expression = "java(tourProjection.getRating())")
  @Mapping(target = "agency.reviewCount", expression = "java(tourProjection.getReviewCount())")
  public abstract TourResponse projectionToResponse(TourProjection tourProjection);

  @Mapping(
      target = "agency",
      expression =
          "java(new AgencyResponse("
              + "tourProjection.getAgencyId(), "
              + "tourProjection.getAgencyName(), "
              + "tourProjection.getAgencyImage(), "
              + "tourProjection.getRating(), "
              + "tourProjection.getReviewCount()))")
  public abstract TourDetailResponse projectionToDetailResponse(
      final TourDetailProjection tourProjection,
      final List<ScheduleDto> schedules,
      final List<InclusionDto> inclusions,
      final List<ImageDto> images);

  public abstract InclusionDto mapInclusion(InclusionProjection inclusion);

  public abstract ScheduleDto mapSchedule(ScheduleProjection schedule);
}
