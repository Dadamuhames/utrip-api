package com.msd.utrip.service.client;

import com.msd.utrip.dto.response.tour.TourResponse;
import com.msd.utrip.repository.redis.SavedTourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavedTourService {
  private final SavedTourRepository savedTourRepository;
}
