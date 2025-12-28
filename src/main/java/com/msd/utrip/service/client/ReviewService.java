package com.msd.utrip.service.client;

import com.msd.utrip.dto.request.ReviewFilterRequest;
import com.msd.utrip.dto.response.ReviewResponse;
import com.msd.utrip.entity.ReviewEntity;
import com.msd.utrip.mapper.ReviewMapper;
import com.msd.utrip.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
  private final ReviewRepository reviewRepository;
  private final ReviewMapper reviewMapper;

  @Transactional(readOnly = true)
  public Page<ReviewResponse> list(final ReviewFilterRequest filter, final Pageable pageable) {
    Page<ReviewEntity> reviews;

    if (filter.tourId() != null) {
      reviews = reviewRepository.findAllByTourId(filter.tourId(), pageable);
    } else {
      reviews = reviewRepository.findAllByAgencyId(filter.agencyId(), pageable);
    }

    return reviews.map(reviewMapper::entityToResponse);
  }
}
