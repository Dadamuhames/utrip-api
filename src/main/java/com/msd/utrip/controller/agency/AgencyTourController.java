package com.msd.utrip.controller.agency;

import com.msd.utrip.dto.request.AgencyTourFilterRequest;
import com.msd.utrip.dto.response.agency.AgencyTourResponse;
import com.msd.utrip.dto.response.tour.TourDetailResponseForAgency;
import com.msd.utrip.entity.agency.AgencyEntity;
import com.msd.utrip.service.agency.AgencyTourViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/agency/tours")
public class AgencyTourController {
  private final AgencyTourViewService tourViewService;

  @GetMapping
  public ResponseEntity<Page<AgencyTourResponse>> list(
      final AgencyTourFilterRequest filter,
      @AuthenticationPrincipal AgencyEntity agency,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer pageSize) {

    Pageable pageable = PageRequest.of(page, pageSize);

    Page<AgencyTourResponse> tours = tourViewService.list(agency, filter, pageable);

    return ResponseEntity.ok(tours);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TourDetailResponseForAgency> one(
      @PathVariable Long id, @AuthenticationPrincipal AgencyEntity agency) {

    TourDetailResponseForAgency response = tourViewService.one(id, agency);

    return ResponseEntity.ok(response);
  }
}
