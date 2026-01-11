package com.msd.utrip.mapper;

import com.msd.utrip.dto.request.AgencyProfileRequest;
import com.msd.utrip.dto.response.AgencyProfileResponse;
import com.msd.utrip.dto.response.agency.AgencyDetailResponse;
import com.msd.utrip.dto.response.agency.AgencySimpleResponse;
import com.msd.utrip.dto.response.file.ImageDto;
import com.msd.utrip.entity.agency.AgencyEntity;
import com.msd.utrip.entity.agency.AgencyInfoEntity;
import com.msd.utrip.entity.field.MultiLanguageText;
import com.msd.utrip.repository.projection.AgencyDetailProjection;
import com.msd.utrip.repository.projection.AgencyProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AgencyMapper {

  AgencySimpleResponse projectionToResponse(AgencyProjection projection);

  @Mapping(target = "images", source = "images")
  @Mapping(target = "videos", source = "videos")
  AgencyDetailResponse projectionToDetailResponse(
      AgencyDetailProjection projection, List<ImageDto> images, List<ImageDto> videos);

  @Mapping(target = "id", source = "entity.id")
  @Mapping(target = "createdAt", source = "entity.createdAt")
  @Mapping(target = "info", expression = "java(mapMultiLangField(info.getInfo()))")
  @Mapping(target = "subtitle", expression = "java(mapMultiLangField(entity.getSubtitle()))")
  AgencyProfileResponse entityToProfileResponse(AgencyEntity entity, AgencyInfoEntity info);

  @Mapping(target = "info", expression = "java(mapMultiLangField(request.info()))")
  void updateAgencyInfo(AgencyProfileRequest request, @MappingTarget AgencyInfoEntity info);

  default MultiLanguageText mapMultiLangField(Map<String, String> value) {
    return new MultiLanguageText(value);
  }

  default Map<String, String> mapMultiLangField(MultiLanguageText value) {
    if (value == null) return null;

    return value.getTexts();
  }
}
