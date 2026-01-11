package com.msd.utrip.controller.client;

import com.msd.utrip.dto.request.UserProfileRequest;
import com.msd.utrip.dto.response.UserProfileResponse;
import com.msd.utrip.entity.user.UserEntity;
import com.msd.utrip.service.profile.UserProfileService;
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
@RequestMapping("/api/v1/client/profile")
public class UserProfileController {
  private final UserProfileService userProfileService;

  @GetMapping
  public ResponseEntity<UserProfileResponse> profile(
      @AuthenticationPrincipal final UserEntity user) {
    UserProfileResponse response = userProfileService.getProfile(user);

    return ResponseEntity.ok(response);
  }

  @PutMapping
  public ResponseEntity<Void> update(
      @Valid @RequestBody final UserProfileRequest request,
      @AuthenticationPrincipal final UserEntity user) {

    userProfileService.updateProfile(user, request);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
