package com.msd.utrip.mapper;

import com.msd.utrip.dto.response.agency.AgencyTourResponse;
import com.msd.utrip.dto.response.file.ImageDto;
import com.msd.utrip.dto.response.tour.InclusionDto;
import com.msd.utrip.dto.response.tour.ScheduleDto;
import com.msd.utrip.dto.response.tour.TourDetailResponse;
import com.msd.utrip.dto.response.tour.TourDetailResponseForAgency;
import com.msd.utrip.dto.response.tour.TourResponse;
import com.msd.utrip.repository.projection.AgencyTourProjection;
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

  public abstract AgencyTourResponse agencyProjectionToResponse(
      AgencyTourProjection tourProjection);

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

  public abstract TourDetailResponseForAgency projectionToDetailResponseForAgency(
      final TourDetailProjection tourProjection,
      final List<ScheduleDto> schedules,
      final List<InclusionDto> inclusions,
      final List<ImageDto> images);

  public abstract InclusionDto mapInclusion(InclusionProjection inclusion);

  public abstract ScheduleDto mapSchedule(ScheduleProjection schedule);
}
