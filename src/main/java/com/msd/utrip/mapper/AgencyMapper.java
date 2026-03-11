package com.msd.utrip.mapper;

import com.msd.utrip.dto.request.admin.AgencyUpdateRequest;
import com.msd.utrip.dto.request.agency.AgencyProfileRequest;
import com.msd.utrip.dto.request.admin.AgencyRequest;
import com.msd.utrip.dto.response.AgencyProfileResponse;
import com.msd.utrip.dto.response.admin.AgencyAdminResponse;
import com.msd.utrip.dto.response.agency.AgencyDetailResponse;
import com.msd.utrip.dto.response.agency.AgencyProfileDetailResponse;
import com.msd.utrip.dto.response.agency.AgencySimpleResponse;
import com.msd.utrip.dto.response.file.ImageDto;
import com.msd.utrip.entity.agency.AgencyEntity;
import com.msd.utrip.entity.agency.AgencyInfoEntity;
import com.msd.utrip.entity.field.MultiLanguageText;
import com.msd.utrip.repository.projection.AgencyDetailProjection;
import com.msd.utrip.repository.projection.AgencyDetailProjectionForClient;
import com.msd.utrip.repository.projection.AgencyProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgencyMapper {

  @Autowired MultilangMapper multilangMapper;

  public abstract AgencySimpleResponse projectionToResponse(AgencyProjection projection);

  @Mapping(
      target = "subtitle",
      expression = "java(multilangMapper.mapMultiLangField(request.subtitle()))")
  public abstract void updateAgency(
      AgencyProfileRequest request, @MappingTarget AgencyEntity entity);

  @Mapping(target = "images", source = "images")
  @Mapping(target = "videos", source = "videos")
  public abstract AgencyDetailResponse projectionToDetailResponse(
      AgencyDetailProjectionForClient projection, List<ImageDto> images, List<ImageDto> videos);

  @Mapping(target = "images", source = "images")
  @Mapping(target = "videos", source = "videos")
  @Mapping(target = "totalParticipantsCount", source = "totalParticipantsCount")
  public abstract AgencyProfileDetailResponse projectionToProfileDetailResponse(
      AgencyDetailProjection projection,
      List<ImageDto> images,
      List<ImageDto> videos,
      Integer totalParticipantsCount);

  @Mapping(target = "id", source = "entity.id")
  @Mapping(target = "createdAt", source = "entity.createdAt")
  @Mapping(target = "info", expression = "java(multilangMapper.mapMultiLangField(info.getInfo()))")
  @Mapping(
      target = "subtitle",
      expression = "java(multilangMapper.mapMultiLangField(entity.getSubtitle()))")
  public abstract AgencyProfileResponse entityToProfileResponse(
      AgencyEntity entity, AgencyInfoEntity info);

  @Mapping(target = "info", expression = "java(multilangMapper.mapMultiLangField(request.info()))")
  public abstract void updateAgencyInfo(
      AgencyProfileRequest request, @MappingTarget AgencyInfoEntity info);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "isActive", ignore = true)
  @Mapping(target = "password", source = "passwordEncoded")
  @Mapping(
      target = "subtitle",
      expression = "java(multilangMapper.mapMultiLangField(request.subtitle()))")
  public abstract AgencyEntity requestToAgency(AgencyRequest request, String passwordEncoded);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "password", source = "passwordEncoded")
  @Mapping(
      target = "subtitle",
      expression = "java(multilangMapper.mapMultiLangField(request.subtitle()))")
  public abstract void updateAgency(
      AgencyUpdateRequest request, String passwordEncoded, @MappingTarget AgencyEntity entity);

  public abstract AgencyAdminResponse entityToAdminResponse(AgencyEntity entity);
}
