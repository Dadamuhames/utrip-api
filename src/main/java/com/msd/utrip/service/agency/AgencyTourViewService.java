package com.msd.utrip.service.agency;

import com.msd.utrip.dto.request.AgencyTourFilterRequest;
import com.msd.utrip.dto.response.agency.AgencyTourResponse;
import com.msd.utrip.dto.response.file.ImageDto;
import com.msd.utrip.dto.response.tour.InclusionDto;
import com.msd.utrip.dto.response.tour.ScheduleDto;
import com.msd.utrip.dto.response.tour.TourDetailResponseForAgency;
import com.msd.utrip.entity.agency.AgencyEntity;
import com.msd.utrip.entity.tour.TourImageEntity;
import com.msd.utrip.exception.EntityNotFoundException;
import com.msd.utrip.mapper.ImageMapper;
import com.msd.utrip.mapper.TourMapper;
import com.msd.utrip.repository.projection.AgencyTourProjection;
import com.msd.utrip.repository.projection.InclusionProjection;
import com.msd.utrip.repository.projection.ScheduleProjection;
import com.msd.utrip.repository.projection.TourDetailProjection;
import com.msd.utrip.repository.tour.InclusionsRepository;
import com.msd.utrip.repository.tour.ScheduleRepository;
import com.msd.utrip.repository.tour.TourFilterRepository;
import com.msd.utrip.repository.tour.TourImageRepository;
import com.msd.utrip.repository.tour.TourRepository;
import com.msd.utrip.service.LanguageExtractService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AgencyTourViewService {
  private final TourRepository tourRepository;
  private final TourFilterRepository tourFilterRepository;
  private final TourMapper tourMapper;
  private final ImageMapper imageMapper;
  private final LanguageExtractService languageExtractService;
  private final InclusionsRepository inclusionsRepository;
  private final ScheduleRepository scheduleRepository;
  private final TourImageRepository tourImageRepository;

  @Transactional(readOnly = true)
  public Page<AgencyTourResponse> list(
      final AgencyEntity agency, final AgencyTourFilterRequest filter, final Pageable pageable) {
    String lang = languageExtractService.getCurrentLanguage();

    Page<AgencyTourProjection> tours =
        tourFilterRepository.findLocalizedForAgency(lang, agency.getId(), filter, pageable);

    return tours.map(tourMapper::agencyProjectionToResponse);
  }

  @Transactional(readOnly = true)
  public TourDetailResponseForAgency one(final Long id, final AgencyEntity agency) {
    String lang = languageExtractService.getCurrentLanguage();

    TourDetailProjection tour =
        tourRepository
            .findByIdLocalizedForAgency(id, agency.getId(), lang)
            .orElseThrow(EntityNotFoundException::new);

    List<TourImageEntity> images = tourImageRepository.findByTourId(id);
    List<InclusionProjection> inclusions = inclusionsRepository.findByTourIdLocalized(id, lang);
    List<ScheduleProjection> schedules = scheduleRepository.findByTourIdLocalized(id, lang);

    List<ImageDto> imageList = images.stream().map(imageMapper::mapTourImage).toList();
    List<InclusionDto> inclusionList = inclusions.stream().map(tourMapper::mapInclusion).toList();
    List<ScheduleDto> scheduleList = schedules.stream().map(tourMapper::mapSchedule).toList();

    return tourMapper.projectionToDetailResponseForAgency(
        tour, scheduleList, inclusionList, imageList);
  }
}
