package com.msd.utrip.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;

public record UserProfileRequest(
    @NotBlank String fullName,
    String image,
    @NotBlank String email,
    String password,
    String newPassword,
    String newPasswordConfirm) {

  @AssertTrue(
      message = "to reset password, current password should be provided and new password confirmed")
  private boolean isPasswordsCorrect() {
    return this.newPassword == null
        || (this.password != null && this.newPassword.equals(this.newPasswordConfirm));
  }
}
