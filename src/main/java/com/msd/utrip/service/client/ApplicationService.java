package com.msd.utrip.service.client;

import com.msd.utrip.dto.request.ApplicationRequest;
import com.msd.utrip.entity.ApplicationEntity;
import com.msd.utrip.entity.TourEntity;
import com.msd.utrip.entity.user.UserEntity;
import com.msd.utrip.mapper.ApplicationMapper;
import com.msd.utrip.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {

  private final ApplicationRepository applicationRepository;
  private final ClientTourService tourService;
  private final ApplicationMapper applicationMapper;

  public void applyForTour(
      final Long tourId, final ApplicationRequest request, final UserEntity user) {
    TourEntity tour = tourService.getActiveTour(tourId, request.personCount());

    ApplicationEntity application = applicationMapper.requestToEntity(request, tour, user);

    applicationRepository.save(application);
  }
}
