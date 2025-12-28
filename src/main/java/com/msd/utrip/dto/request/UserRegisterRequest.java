package com.msd.utrip.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterRequest(
    @NotBlank String fullName,
    @NotBlank String phone,
    @NotBlank String password,
    @NotBlank String passwordConfirm,
    @NotBlank @Size(min = 5, max = 5) String otpCode) {

  @AssertTrue(message = "Passwords should match")
  private boolean isPasswordsMatch() {
    return password.equals(passwordConfirm);
  }
}
