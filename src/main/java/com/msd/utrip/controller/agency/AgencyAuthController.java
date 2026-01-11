package com.msd.utrip.controller.agency;

import com.msd.utrip.dto.request.AgencyLoginRequest;
import com.msd.utrip.dto.response.TokenResponse;
import com.msd.utrip.service.auth.AgencyAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/agency/auth")
public class AgencyAuthController {
  private final AgencyAuthService agencyAuthService;

  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@Valid @RequestBody AgencyLoginRequest request) {
    TokenResponse response = agencyAuthService.login(request);

    return ResponseEntity.ok(response);
  }
}
