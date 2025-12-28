package com.msd.utrip.mapper;

import com.msd.utrip.dto.request.ApplicationRequest;
import com.msd.utrip.entity.ApplicationEntity;
import com.msd.utrip.entity.TourEntity;
import com.msd.utrip.entity.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "phone", source = "request.phone")
  @Mapping(target = "tour", source = "tour")
  @Mapping(target = "user", source = "user")
  ApplicationEntity requestToEntity(ApplicationRequest request, TourEntity tour, UserEntity user);
}
