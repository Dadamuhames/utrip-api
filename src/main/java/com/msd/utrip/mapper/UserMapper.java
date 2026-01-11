package com.msd.utrip.mapper;

import com.msd.utrip.dto.request.UserProfileRequest;
import com.msd.utrip.dto.request.UserRegisterRequest;
import com.msd.utrip.dto.response.UserProfileResponse;
import com.msd.utrip.dto.response.UserResponse;
import com.msd.utrip.entity.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "telegramId", source = "telegramId")
  public abstract UserEntity mapRequestToUser(UserRegisterRequest request, Long telegramId);

  public abstract UserResponse entityToUser(UserEntity user);

  public abstract UserProfileResponse userToProfile(UserEntity user);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "telegramId", ignore = true)
  public abstract void updateProfile(UserProfileRequest request, @MappingTarget UserEntity user);
}
