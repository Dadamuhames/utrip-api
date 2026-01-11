package com.msd.utrip.controller.agency;

import com.msd.utrip.dto.request.AgencyProfileRequest;
import com.msd.utrip.dto.response.AgencyProfileResponse;
import com.msd.utrip.dto.response.agency.AgencyDetailResponse;
import com.msd.utrip.entity.agency.AgencyEntity;
import com.msd.utrip.service.agency.AgencyProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/agency/profile")
public class AgencyProfileController {

  private final AgencyProfileService agencyProfileService;

  @GetMapping
  public ResponseEntity<AgencyDetailResponse> getProfile(
      @AuthenticationPrincipal AgencyEntity agency) {
    AgencyDetailResponse response = agencyProfileService.getProfile(agency);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/form")
  public ResponseEntity<AgencyProfileResponse> getProfileForForm(
      @AuthenticationPrincipal AgencyEntity agency) {
    AgencyProfileResponse response = agencyProfileService.getProfileForm(agency);

    return ResponseEntity.ok(response);
  }

  @PutMapping
  public ResponseEntity<Void> update(
      @Valid @RequestBody AgencyProfileRequest request,
      @AuthenticationPrincipal AgencyEntity agency) {

    agencyProfileService.updateProfile(agency, request);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
