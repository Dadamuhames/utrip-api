package com.msd.utrip.service.admin;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.dto.request.admin.CountryRequest;
import com.msd.utrip.dto.response.admin.CountryAdminResponse;
import com.msd.utrip.entity.CountryEntity;
import com.msd.utrip.entity.field.MultiLanguageText;
import com.msd.utrip.exception.EntityNotFoundException;
import com.msd.utrip.mapper.RegionMapper;
import com.msd.utrip.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CountryAdminService {

  private final CountryRepository countryRepository;
  private final RegionMapper regionMapper;

  @Transactional(readOnly = true)
  public Page<CountryAdminResponse> list(final Pageable pageable) {
    Page<CountryEntity> countries = countryRepository.findAll(pageable);

    return countries.map(regionMapper::entityToAdminResponse);
  }

  private CountryEntity getInstance(final Long id) {
    return countryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  @Transactional(readOnly = true)
  public CountryAdminResponse getOne(final Long id) {

    CountryEntity country = getInstance(id);

    return regionMapper.entityToAdminResponse(country);
  }

  @Transactional
  public CountryAdminResponse create(final CountryRequest request) {
    CountryEntity country = regionMapper.countryRequestToEntity(request);

    country = countryRepository.save(country);

    return regionMapper.entityToAdminResponse(country);
  }

  @Transactional
  public CountryAdminResponse update(final Long id, final CountryRequest request) {
    CountryEntity country = getInstance(id);

    country.setName(new MultiLanguageText(request.name()));

    countryRepository.save(country);

    return regionMapper.entityToAdminResponse(country);
  }
}
