package com.msd.utrip.mapper;


import com.msd.utrip.dto.request.admin.CountryRequest;
import com.msd.utrip.dto.request.admin.RegionRequest;
import com.msd.utrip.dto.response.admin.CountryAdminResponse;
import com.msd.utrip.dto.response.admin.RegionAdminResponse;
import com.msd.utrip.entity.CountryEntity;
import com.msd.utrip.entity.RegionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class RegionMapper {

    @Autowired
    MultilangMapper multilangMapper;

    @Mapping(target = "name", expression = "java(entity.getName().getTexts())")
    public abstract CountryAdminResponse entityToAdminResponse(CountryEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "name", expression = "java(multilangMapper.mapMultiLangField(request.name()))")
    public abstract CountryEntity countryRequestToEntity(CountryRequest request);

    @Mapping(target = "name", expression = "java(entity.getName().getTexts())")
    public abstract RegionAdminResponse regionEntityToAdminResponse(RegionEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "country", source = "country")
    @Mapping(target = "name", expression = "java(multilangMapper.mapMultiLangField(request.name()))")
    public abstract RegionEntity regionRequestToEntity(RegionRequest request, CountryEntity country);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "country", source = "country")
    @Mapping(target = "name", expression = "java(multilangMapper.mapMultiLangField(request.name()))")
    public  abstract void updateRegion(RegionRequest request, CountryEntity country, @MappingTarget RegionEntity entity);

}
