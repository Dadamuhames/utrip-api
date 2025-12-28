package com.msd.utrip.controller;

import com.msd.utrip.dto.request.TourFilterRequest;
import com.msd.utrip.dto.response.TourDetailResponse;
import com.msd.utrip.dto.response.TourResponse;
import com.msd.utrip.service.client.ClientTourService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client/tours")
public class ClientTourController {
  private final ClientTourService clientTourService;

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
}
