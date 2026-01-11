package com.msd.utrip.controller.client;

import com.msd.utrip.dto.request.ReviewFilterRequest;
import com.msd.utrip.dto.request.ReviewRequest;
import com.msd.utrip.dto.response.ReviewResponse;
import com.msd.utrip.entity.user.UserEntity;
import com.msd.utrip.service.client.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client/reviews")
public class ReviewController {
  private final ReviewService reviewService;

  @GetMapping
  public ResponseEntity<Page<ReviewResponse>> list(
      final ReviewFilterRequest filter,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer pageSize) {

    Pageable pageable = PageRequest.of(page, pageSize);

    Page<ReviewResponse> reviews = reviewService.list(filter, pageable);

    return ResponseEntity.ok(reviews);
  }

  @PostMapping
  public ResponseEntity<Void> create(
      @Valid @RequestBody ReviewRequest request, @AuthenticationPrincipal UserEntity user) {

    reviewService.createReview(request, user);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
