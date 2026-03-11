package com.msd.utrip.controller.admin;

import com.msd.utrip.dto.request.agency.LoginRequest;
import com.msd.utrip.dto.response.TokenResponse;
import com.msd.utrip.service.auth.AdminAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/auth")
public class AdminAuthController {

  private final AdminAuthService adminAuthService;

  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {

    TokenResponse response = adminAuthService.login(request);

    return ResponseEntity.ok(response);
  }
}
