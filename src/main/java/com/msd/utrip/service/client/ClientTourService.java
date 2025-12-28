package com.msd.utrip.service.client;

import com.msd.utrip.dto.request.TourFilterRequest;
import com.msd.utrip.dto.response.ImageDto;
import com.msd.utrip.dto.response.InclusionDto;
import com.msd.utrip.dto.response.ScheduleDto;
import com.msd.utrip.dto.response.TourDetailResponse;
import com.msd.utrip.dto.response.TourResponse;
import com.msd.utrip.entity.InclusionEntity;
import com.msd.utrip.entity.ScheduleEntity;
import com.msd.utrip.entity.TourEntity;
import com.msd.utrip.entity.TourImageEntity;
import com.msd.utrip.exception.EntityNotFoundException;
import com.msd.utrip.exception.TourNotAvailableException;
import com.msd.utrip.mapper.ImageMapper;
import com.msd.utrip.mapper.TourMapper;
import com.msd.utrip.repository.InclusionsRepository;
import com.msd.utrip.repository.ScheduleRepository;
import com.msd.utrip.repository.TourFilterRepository;
import com.msd.utrip.repository.TourImageRepository;
import com.msd.utrip.repository.TourRepository;
import com.msd.utrip.repository.projection.InclusionProjection;
import com.msd.utrip.repository.projection.ScheduleProjection;
import com.msd.utrip.repository.projection.TourDetailProjection;
import com.msd.utrip.repository.projection.TourProjection;
import com.msd.utrip.service.LanguageExtractService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientTourService {
  private final TourFilterRepository tourFilterRepository;
  private final TourRepository tourRepository;
  private final TourImageRepository tourImageRepository;
  private final TourMapper tourMapper;
  private final ImageMapper imageMapper;
  private final LanguageExtractService languageExtractService;
  private final InclusionsRepository inclusionsRepository;
  private final ScheduleRepository scheduleRepository;

  @Transactional(readOnly = true)
  public Page<TourResponse> list(final TourFilterRequest filter, final Pageable pageable) {
    String lang = languageExtractService.getCurrentLanguage();

    Page<TourProjection> tours = tourFilterRepository.findAllLocalized(lang, filter, pageable);

    return tours.map(tourMapper::projectionToResponse);
  }

  public TourEntity getInstance(final Long id) {
    return tourRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  @Transactional(readOnly = true)
  public TourDetailResponse one(final Long id) {
    String lang = languageExtractService.getCurrentLanguage();

    TourDetailProjection tour =
        tourRepository.findByIdLocalized(id, lang).orElseThrow(EntityNotFoundException::new);

    List<TourImageEntity> images = tourImageRepository.findByTourId(id);
    List<InclusionProjection> inclusions = inclusionsRepository.findByTourIdLocalized(id, lang);
    List<ScheduleProjection> schedules = scheduleRepository.findByTourIdLocalized(id, lang);

    List<ImageDto> imageList = images.stream().map(imageMapper::mapTourImage).toList();
    List<InclusionDto> inclusionList = inclusions.stream().map(tourMapper::mapInclusion).toList();
    List<ScheduleDto> scheduleList = schedules.stream().map(tourMapper::mapSchedule).toList();

//    return tourMapper.projectionToDetailResponse(tour, scheduleList, inclusionList, imageList);
      return null;
  }

  public TourEntity getActiveTour(final Long id, final Integer personCount) {
    return tourRepository
        .findByIdActive(id, personCount)
        .orElseThrow(TourNotAvailableException::new);
  }
}
