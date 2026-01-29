package com.msd.utrip.service.agency;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.dto.request.TourCreateRequest;
import com.msd.utrip.entity.agency.AgencyEntity;
import com.msd.utrip.entity.tour.InclusionEntity;
import com.msd.utrip.entity.tour.ScheduleEntity;
import com.msd.utrip.entity.tour.TourEntity;
import com.msd.utrip.entity.tour.TourImageEntity;
import com.msd.utrip.exception.CategoryInvalidException;
import com.msd.utrip.exception.EntityNotFoundException;
import com.msd.utrip.exception.RegionInvalidException;
import com.msd.utrip.mapper.TourMapper;
import com.msd.utrip.repository.CategoryRepository;
import com.msd.utrip.repository.RegionRepository;
import com.msd.utrip.repository.tour.InclusionsRepository;
import com.msd.utrip.repository.tour.ScheduleRepository;
import com.msd.utrip.repository.tour.TourImageRepository;
import com.msd.utrip.repository.tour.TourRepository;
import com.msd.utrip.service.file.FileCleanupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgencyTourService {
  private final TourRepository tourRepository;
  private final TourMapper tourMapper;
  private final TourImageRepository tourImageRepository;
  private final InclusionsRepository inclusionRepository;
  private final ScheduleRepository scheduleRepository;
  private final CategoryRepository categoryRepository;
  private final RegionRepository regionRepository;
  private final FileCleanupService cleanupService;

  @Transactional
  public void createTour(final TourCreateRequest request, final AgencyEntity agency) {
    TourEntity tour = tourMapper.toEntity(request, agency);

    var category =
        categoryRepository
            .findById(request.categoryId())
            .orElseThrow(() -> new CategoryInvalidException(ErrorCode.CATEGORY_INVALID_EXCEPTION));

    var region =
        regionRepository
            .findById(request.regionId())
            .orElseThrow(() -> new RegionInvalidException(ErrorCode.REGION_INVALID_EXCEPTION));

    tour.setCategory(category);
    tour.setRegion(region);

    TourEntity savedTour = tourRepository.saveAndFlush(tour);

    if (request.images() != null) {
      List<TourImageEntity> images =
          request.images().stream().map(path -> new TourImageEntity(savedTour, path)).toList();

      request.images().forEach(cleanupService::deleteByFileAndBucket);

      tourImageRepository.saveAll(images);
    }

    if (request.schedule() != null) {
      List<ScheduleEntity> schedules =
          request.schedule().stream()
              .map(dto -> tourMapper.scheduleRequestToEntity(dto, savedTour))
              .toList();

      scheduleRepository.saveAll(schedules);
    }

    if (request.inclusions() != null) {
      List<InclusionEntity> inclusions =
          request.inclusions().stream()
              .map(dto -> tourMapper.inclusionRequestToEntity(dto, savedTour))
              .toList();
      inclusionRepository.saveAll(inclusions);
    }

    cleanupService.deleteByFileAndBucket(request.image());
  }
}
