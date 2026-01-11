package com.msd.utrip.service.agency;

import com.msd.utrip.dto.request.AgencyProfileRequest;
import com.msd.utrip.dto.response.AgencyProfileResponse;
import com.msd.utrip.dto.response.agency.AgencyDetailResponse;
import com.msd.utrip.dto.response.file.ImageDto;
import com.msd.utrip.entity.agency.AgencyEntity;
import com.msd.utrip.entity.agency.AgencyImageEntity;
import com.msd.utrip.entity.agency.AgencyInfoEntity;
import com.msd.utrip.entity.agency.AgencyVideoEntity;
import com.msd.utrip.entity.field.MultiLanguageText;
import com.msd.utrip.exception.EntityNotFoundException;
import com.msd.utrip.mapper.AgencyMapper;
import com.msd.utrip.mapper.ImageMapper;
import com.msd.utrip.repository.agency.AgencyImageRepository;
import com.msd.utrip.repository.agency.AgencyInfoRepository;
import com.msd.utrip.repository.agency.AgencyRepository;
import com.msd.utrip.repository.agency.AgencyVideoRepository;
import com.msd.utrip.repository.projection.AgencyDetailProjection;
import com.msd.utrip.service.LanguageExtractService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgencyProfileService {
  private final AgencyRepository agencyRepository;
  private final AgencyImageRepository imageRepository;
  private final AgencyVideoRepository videoRepository;
  private final AgencyInfoRepository infoRepository;
  private final LanguageExtractService languageExtractService;
  private final ImageMapper imageMapper;
  private final AgencyMapper agencyMapper;

  @Transactional(readOnly = true)
  public AgencyDetailResponse getProfile(final AgencyEntity agency) {
    String lang = languageExtractService.getCurrentLanguage();

    AgencyDetailProjection detailProjection =
        agencyRepository
            .findByIdLocalized(agency.getId(), lang)
            .orElseThrow(EntityNotFoundException::new);

    Pageable filePagination = PageRequest.of(0, 20);

    List<AgencyImageEntity> images =
        imageRepository.findAllByAgencyId(agency.getId(), filePagination).getContent();
    List<ImageDto> imagesDto = images.stream().map(imageMapper::mapAgencyImage).toList();

    List<AgencyVideoEntity> videos =
        videoRepository.findAllByAgencyId(agency.getId(), filePagination).getContent();
    List<ImageDto> videoDto = videos.stream().map(imageMapper::mapAgencyVideo).toList();

    return agencyMapper.projectionToDetailResponse(detailProjection, imagesDto, videoDto);
  }

  @Transactional(readOnly = true)
  public AgencyProfileResponse getProfileForm(final AgencyEntity agency) {
    AgencyInfoEntity info =
        infoRepository
            .findByAgencyId(agency.getId())
            .orElse(AgencyInfoEntity.builder().agency(agency).build());

    return agencyMapper.entityToProfileResponse(agency, info);
  }

  @Transactional
  public void updateProfile(final AgencyEntity agency, final AgencyProfileRequest request) {
    AgencyInfoEntity info =
        infoRepository
            .findByAgencyId(agency.getId())
            .orElse(AgencyInfoEntity.builder().agency(agency).build());

    agency.setName(request.name());
    agency.setSubtitle(new MultiLanguageText(request.subtitle()));

    agencyMapper.updateAgencyInfo(request, info);

    agencyRepository.save(agency);
    infoRepository.save(info);

    if (!request.images().isEmpty()) {
      List<AgencyImageEntity> images =
          request.images().stream().map(image -> new AgencyImageEntity(agency, image)).toList();

      imageRepository.saveAll(images);
    }

    if (!request.videos().isEmpty()) {
      List<AgencyVideoEntity> videos =
          request.videos().stream().map(video -> new AgencyVideoEntity(agency, video)).toList();

      videoRepository.saveAll(videos);
    }
  }
}
