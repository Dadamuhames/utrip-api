package com.msd.utrip.mapper;

import com.msd.utrip.dto.request.ApplicationRequest;
import com.msd.utrip.dto.response.ApplicationResponse;
import com.msd.utrip.dto.response.tour.TourSimpleResponse;
import com.msd.utrip.entity.ApplicationEntity;
import com.msd.utrip.entity.tour.TourEntity;
import com.msd.utrip.entity.user.UserEntity;
import com.msd.utrip.repository.projection.ApplicationProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ApplicationMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "phone", source = "request.phone")
  @Mapping(target = "tour", source = "tour")
  @Mapping(target = "user", source = "user")
  @Mapping(target = "totalPrice", expression = "java(calculateTotalPrice(request, tour))")
  public abstract ApplicationEntity requestToEntity(
      ApplicationRequest request, TourEntity tour, UserEntity user);

  @Mapping(target = "tour", expression = "java(mapTour(entity))")
  public abstract ApplicationResponse entityToResponse(ApplicationProjection entity, String lang);

  public TourSimpleResponse mapTour(ApplicationProjection projection) {
    return new TourSimpleResponse(
        projection.getTourId(),
        projection.getTitle(),
        projection.getStartDate(),
        projection.getEndDate());
  }

  public BigDecimal calculateTotalPrice(ApplicationRequest request, TourEntity tour) {
    return tour.getPrice()
        .multiply(BigDecimal.valueOf(request.personCount()))
        .setScale(2, RoundingMode.HALF_UP);
  }
}
