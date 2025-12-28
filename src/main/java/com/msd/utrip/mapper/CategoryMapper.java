package com.msd.utrip.mapper;

import com.msd.utrip.dto.response.CategoryResponse;
import com.msd.utrip.repository.projection.CategoryProjection;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

  CategoryResponse projectionToResponse(CategoryProjection projection);
}
