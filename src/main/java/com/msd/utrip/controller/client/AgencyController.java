package com.msd.utrip.controller.client;

import com.msd.utrip.dto.response.agency.AgencyDetailResponse;
import com.msd.utrip.dto.response.agency.AgencySimpleResponse;
import com.msd.utrip.service.client.ClientAgencyService;
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
@RequestMapping("/api/v1/client/agencies")
public class AgencyController {
  private final ClientAgencyService agencyService;

  @GetMapping
  public ResponseEntity<Page<AgencySimpleResponse>> list(
      @RequestParam(defaultValue = "") String search,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer pageSize) {

    Pageable pageable = PageRequest.of(page, pageSize);

    Page<AgencySimpleResponse> agencies = agencyService.list(search, pageable);

    return ResponseEntity.ok(agencies);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AgencyDetailResponse> one(@PathVariable final Long id) {
    AgencyDetailResponse response = agencyService.getOne(id);

    return ResponseEntity.ok(response);
  }
}
