package com.msd.utrip.controller;

import com.msd.utrip.dto.request.UserLoginRequest;
import com.msd.utrip.dto.request.UserRegisterRequest;
import com.msd.utrip.dto.response.TokenResponse;
import com.msd.utrip.service.auth.UserAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client/auth")
public class ClientAuthController {
  private final UserAuthService authService;

  @PostMapping("/registration")
  public ResponseEntity<TokenResponse> register(@Valid @RequestBody UserRegisterRequest request) {
    TokenResponse response = authService.register(request);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@Valid @RequestBody UserLoginRequest request) {

    TokenResponse response = authService.login(request);

    return ResponseEntity.ok(response);
  }
}
