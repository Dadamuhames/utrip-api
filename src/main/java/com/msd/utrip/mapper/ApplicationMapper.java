package com.msd.utrip.mapper;

import com.msd.utrip.dto.request.ApplicationRequest;
import com.msd.utrip.dto.response.ApplicationResponse;
import com.msd.utrip.dto.response.tour.TourSimpleResponse;
import com.msd.utrip.entity.ApplicationEntity;
import com.msd.utrip.entity.tour.TourEntity;
import com.msd.utrip.entity.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ApplicationMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "phone", source = "request.phone")
  @Mapping(target = "tour", source = "tour")
  @Mapping(target = "user", source = "user")
  public abstract ApplicationEntity requestToEntity(
      ApplicationRequest request, TourEntity tour, UserEntity user);

  @Mapping(target = "tour", expression = "java(mapTour(lang, entity.getTour()))")
  public abstract ApplicationResponse entityToResponse(ApplicationEntity entity, String lang);

  public TourSimpleResponse mapTour(String lang, TourEntity tour) {
    return new TourSimpleResponse(tour.getId(), tour.getTitle().get(lang), tour.getStartDate());
  }
}
