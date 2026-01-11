package com.msd.utrip.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PasswordResetRequest(
    @NotBlank String phone,
    @NotBlank String newPassword,
    @NotBlank String newPasswordConfirm,
    @NotBlank @Size(min = 5, max = 5) String code) {

  @AssertTrue(message = "passwords should match")
  private boolean isPasswordConfirmed() {
    return this.newPassword.equals(this.newPasswordConfirm);
  }
}
