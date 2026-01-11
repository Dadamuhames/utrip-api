package com.msd.utrip.service.client;

import com.msd.utrip.dto.request.ReviewFilterRequest;
import com.msd.utrip.dto.request.ReviewRequest;
import com.msd.utrip.dto.response.ReviewResponse;
import com.msd.utrip.entity.ReviewEntity;
import com.msd.utrip.entity.agency.AgencyEntity;
import com.msd.utrip.entity.user.UserEntity;
import com.msd.utrip.exception.AgencyInvalidException;
import com.msd.utrip.mapper.ReviewMapper;
import com.msd.utrip.repository.agency.AgencyRepository;
import com.msd.utrip.repository.ReviewRepository;
import com.msd.utrip.repository.tour.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
  private final ReviewRepository reviewRepository;
  private final TourRepository tourRepository;
  private final AgencyRepository agencyRepository;
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

  @Transactional
  public void createReview(final ReviewRequest request, final UserEntity user) {
    AgencyEntity agency =
        agencyRepository.findById(request.agencyId()).orElseThrow(AgencyInvalidException::new);

    boolean canLeaveReview =
        tourRepository.existsByAgencyIdAndUserIdAndCompleted(request.agencyId(), user.getId());

    boolean reviewExists =
        reviewRepository.existsByUserIdAndAgencyId(user.getId(), request.agencyId());

    if (!canLeaveReview || reviewExists) throw new AgencyInvalidException();

    ReviewEntity review = reviewMapper.requestToRequest(request, agency, user);

    reviewRepository.save(review);
  }
}
