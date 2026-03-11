package com.msd.utrip.service.admin;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.dto.request.admin.RegionRequest;
import com.msd.utrip.dto.response.admin.RegionAdminResponse;
import com.msd.utrip.entity.CountryEntity;
import com.msd.utrip.entity.RegionEntity;
import com.msd.utrip.exception.CountryInvalidException;
import com.msd.utrip.exception.EntityNotFoundException;
import com.msd.utrip.mapper.RegionMapper;
import com.msd.utrip.repository.CountryRepository;
import com.msd.utrip.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.EncoderException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegionAdminService {

  private final RegionRepository regionRepository;
  private final CountryRepository countryRepository;
  private final RegionMapper regionMapper;

  @Transactional(readOnly = true)
  public Page<RegionAdminResponse> list(final Pageable pageable) {

    Page<RegionEntity> regions = regionRepository.findAll(pageable);

    return regions.map(regionMapper::regionEntityToAdminResponse);
  }

  private RegionEntity getInstance(Long id) {
    return regionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  private CountryEntity getCountry(Long countryId) {
    return countryRepository
        .findById(countryId)
        .orElseThrow(() -> new CountryInvalidException(ErrorCode.COUNTRY_INVALID_EXCEPTION));
  }

  @Transactional(readOnly = true)
  public RegionAdminResponse getOne(Long id) {

    RegionEntity region = getInstance(id);

    return regionMapper.regionEntityToAdminResponse(region);
  }

  @Transactional
  public RegionAdminResponse create(final RegionRequest request) {
    CountryEntity country = getCountry(request.countryId());

    RegionEntity region = regionMapper.regionRequestToEntity(request, country);

    region = regionRepository.save(region);

    return regionMapper.regionEntityToAdminResponse(region);
  }

  @Transactional
  public RegionAdminResponse update(Long id, RegionRequest request) {

    RegionEntity region = getInstance(id);
    CountryEntity country = getCountry(request.countryId());

    regionMapper.updateRegion(request, country, region);

    regionRepository.save(region);

    return regionMapper.regionEntityToAdminResponse(region);
  }
}
