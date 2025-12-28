package com.msd.utrip.service.client;

import com.msd.utrip.dto.response.AgencyDetailResponse;
import com.msd.utrip.dto.response.AgencySimpleResponse;
import com.msd.utrip.dto.response.ImageDto;
import com.msd.utrip.entity.AgencyImageEntity;
import com.msd.utrip.exception.EntityNotFoundException;
import com.msd.utrip.mapper.AgencyMapper;
import com.msd.utrip.mapper.ImageMapper;
import com.msd.utrip.repository.AgencyImageRepository;
import com.msd.utrip.repository.AgencyRepository;
import com.msd.utrip.repository.projection.AgencyDetailProjection;
import com.msd.utrip.repository.projection.AgencyProjection;
import com.msd.utrip.service.LanguageExtractService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientAgencyService {
  private final AgencyRepository agencyRepository;
  private final LanguageExtractService languageExtractService;
  private final AgencyImageRepository agencyImageRepository;
  private final ImageMapper imageMapper;
  private final AgencyMapper agencyMapper;

  @Transactional(readOnly = true)
  public Page<AgencySimpleResponse> list(final Pageable pageable) {
    String lang = languageExtractService.getCurrentLanguage();

    Page<AgencyProjection> agencies = agencyRepository.findAllActives(lang, pageable);

    return agencies.map(agencyMapper::projectionToResponse);
  }

  public AgencyDetailResponse getOne(final Long id) {
    String lang = languageExtractService.getCurrentLanguage();

    AgencyDetailProjection detailProjection =
        agencyRepository.findByIdLocalized(id, lang).orElseThrow(EntityNotFoundException::new);

    List<AgencyImageEntity> images = agencyImageRepository.findAllByAgencyId(id);
    List<ImageDto> imagesDto = images.stream().map(imageMapper::mapAgencyImage).toList();

    return agencyMapper.projectionToDetailResponse(detailProjection, imagesDto);
  }
}
