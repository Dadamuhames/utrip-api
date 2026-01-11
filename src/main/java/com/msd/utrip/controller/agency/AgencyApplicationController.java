package com.msd.utrip.controller.agency;

import com.msd.utrip.dto.request.ApplicationFilter;
import com.msd.utrip.dto.response.ApplicationResponse;
import com.msd.utrip.entity.agency.AgencyEntity;
import com.msd.utrip.service.agency.AgencyApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/agency/applications")
public class AgencyApplicationController {
  private final AgencyApplicationService applicationService;

  @GetMapping
  public ResponseEntity<Page<ApplicationResponse>> list(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int pageSize,
      ApplicationFilter filter,
      @AuthenticationPrincipal AgencyEntity agency) {

    Pageable pageable = PageRequest.of(page, pageSize);

    Page<ApplicationResponse> applications = applicationService.list(filter, agency, pageable);

    return ResponseEntity.ok(applications);
  }
}
