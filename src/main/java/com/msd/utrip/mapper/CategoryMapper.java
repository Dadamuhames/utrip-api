package com.msd.utrip.mapper;

import com.msd.utrip.dto.request.admin.CategoryRequest;
import com.msd.utrip.dto.response.CategoryResponse;
import com.msd.utrip.dto.response.admin.CategoryAdminResponse;
import com.msd.utrip.entity.CategoryEntity;
import com.msd.utrip.repository.projection.CategoryProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CategoryMapper {

  @Autowired MultilangMapper multilangMapper;

  public abstract CategoryResponse projectionToResponse(CategoryProjection projection);

  @Mapping(
      target = "title",
      expression = "java(multilangMapper.mapMultiLangField(entity.getTitle()))")
  public abstract CategoryAdminResponse entityToAdminResponse(CategoryEntity entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(
      target = "title",
      expression = "java(multilangMapper.mapMultiLangField(request.title()))")
  public abstract CategoryEntity requestToEntity(CategoryRequest request);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(
      target = "title",
      expression = "java(multilangMapper.mapMultiLangField(request.title()))")
  public abstract void updateEntity(CategoryRequest request, @MappingTarget CategoryEntity entity);
}
