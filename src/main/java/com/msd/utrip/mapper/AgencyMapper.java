package com.msd.utrip.mapper;

import com.msd.utrip.dto.response.AgencyDetailResponse;
import com.msd.utrip.dto.response.AgencySimpleResponse;
import com.msd.utrip.dto.response.ImageDto;
import com.msd.utrip.repository.projection.AgencyDetailProjection;
import com.msd.utrip.repository.projection.AgencyProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AgencyMapper {

  AgencySimpleResponse projectionToResponse(AgencyProjection projection);

  @Mapping(target = "images", source = "images")
  AgencyDetailResponse projectionToDetailResponse(
      AgencyDetailProjection projection, List<ImageDto> images);
}
