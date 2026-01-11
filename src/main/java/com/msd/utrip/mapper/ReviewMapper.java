package com.msd.utrip.mapper;

import com.msd.utrip.dto.request.ReviewRequest;
import com.msd.utrip.dto.response.ReviewResponse;
import com.msd.utrip.entity.ReviewEntity;
import com.msd.utrip.entity.agency.AgencyEntity;
import com.msd.utrip.entity.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ReviewMapper {

  @Autowired public UserMapper userMapper;

  @Mapping(target = "user", expression = "java(userMapper.entityToUser(entity.getUser()))")
  public abstract ReviewResponse entityToResponse(ReviewEntity entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "agency", source = "agency")
  @Mapping(target = "user", source = "user")
  public abstract ReviewEntity requestToRequest(
      ReviewRequest request, AgencyEntity agency, UserEntity user);
}
