package com.msd.utrip.controller.client;

import com.msd.utrip.dto.request.ApplicationRequest;
import com.msd.utrip.dto.request.TourFilterRequest;
import com.msd.utrip.dto.response.tour.TourDetailResponse;
import com.msd.utrip.dto.response.tour.TourResponse;
import com.msd.utrip.entity.user.UserEntity;
import com.msd.utrip.service.client.ApplicationService;
import com.msd.utrip.service.client.ClientTourService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client/tours")
public class ClientTourController {
  private final ClientTourService clientTourService;
  private final ApplicationService applicationService;

  @GetMapping
  public ResponseEntity<Page<TourResponse>> list(
      final TourFilterRequest filter,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer pageSize) {

    Pageable pageable = PageRequest.of(page, pageSize);

    Page<TourResponse> tours = clientTourService.list(filter, pageable);

    return ResponseEntity.ok(tours);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TourDetailResponse> getOne(@PathVariable final Long id) {
    TourDetailResponse response = clientTourService.one(id);

    return ResponseEntity.ok(response);
  }

  @PostMapping("/{id}/application")
  public ResponseEntity<Void> apply(
      @PathVariable final Long id,
      @Valid @RequestBody final ApplicationRequest request,
      @AuthenticationPrincipal final UserEntity user) {

    applicationService.applyForTour(id, request, user);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
