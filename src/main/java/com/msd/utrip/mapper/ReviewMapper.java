package com.msd.utrip.mapper;

import com.msd.utrip.dto.response.ReviewResponse;
import com.msd.utrip.entity.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ReviewMapper {

  @Autowired public UserMapper userMapper;

  @Mapping(target = "user", expression = "java(userMapper.entityToUser(entity.getUser()))")
  public abstract ReviewResponse entityToResponse(ReviewEntity entity);
}
