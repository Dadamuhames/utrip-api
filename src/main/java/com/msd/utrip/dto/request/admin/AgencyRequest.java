package com.msd.utrip.dto.request.admin;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record AgencyRequest(
    @NotBlank String login,
    @NotBlank String name,
    @NotBlank String password,
    @NotBlank String passwordConfirm,
    @NotNull Map<String, String> subtitle) {

  @AssertTrue(message = "passwords should match")
  private boolean isPasswordsMatch() {
    return this.password.equals(this.passwordConfirm);
  }
}
