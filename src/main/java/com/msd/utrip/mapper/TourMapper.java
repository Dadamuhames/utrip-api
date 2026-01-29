package com.msd.utrip.mapper;

import com.msd.utrip.dto.request.InclusionRequestDto;
import com.msd.utrip.dto.request.ScheduleRequestDto;
import com.msd.utrip.dto.request.TourCreateRequest;
import com.msd.utrip.dto.response.agency.AgencyTourResponse;
import com.msd.utrip.dto.response.file.ImageDto;
import com.msd.utrip.dto.response.tour.InclusionDto;
import com.msd.utrip.dto.response.tour.ScheduleDto;
import com.msd.utrip.dto.response.tour.TourDetailResponse;
import com.msd.utrip.dto.response.tour.TourDetailResponseForAgency;
import com.msd.utrip.dto.response.tour.TourResponse;
import com.msd.utrip.entity.agency.AgencyEntity;
import com.msd.utrip.entity.field.MultiLanguageText;
import com.msd.utrip.entity.tour.InclusionEntity;
import com.msd.utrip.entity.tour.ScheduleEntity;
import com.msd.utrip.entity.tour.TourEntity;
import com.msd.utrip.repository.projection.AgencyTourProjection;
import com.msd.utrip.repository.projection.InclusionProjection;
import com.msd.utrip.repository.projection.ScheduleProjection;
import com.msd.utrip.repository.projection.TourDetailProjection;
import com.msd.utrip.repository.projection.TourProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Map;

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

  @Mapping(target = "title", expression = "java(mapMultilang(request.title()))")
  @Mapping(target = "subtitle", expression = "java(mapMultilang(request.subtitle()))")
  @Mapping(target = "info", expression = "java(mapMultilang(request.info()))")
  @Mapping(
      target = "additionalInfo",
      expression = "java(mapMultilang(request.additionalInfo()))")
  @Mapping(target = "agency", source = "agency")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "image", source = "request.image")
  public abstract TourEntity toEntity(TourCreateRequest request, AgencyEntity agency);

  @Mapping(target = "title", expression = "java(mapMultilang(dto.title()))")
  @Mapping(target = "subtitle", expression = "java(mapMultilang(dto.subtitle()))")
  @Mapping(target = "tour", source = "tour")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  public abstract ScheduleEntity scheduleRequestToEntity(ScheduleRequestDto dto, TourEntity tour);

  @Mapping(target = "title", expression = "java(mapMultilang(dto.title()))")
  @Mapping(target = "tour", source = "tour")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  public abstract InclusionEntity inclusionRequestToEntity(InclusionRequestDto dto, TourEntity tour);


  public MultiLanguageText mapMultilang(Map<String, String> text) {
      return new MultiLanguageText(text);
  }
}
