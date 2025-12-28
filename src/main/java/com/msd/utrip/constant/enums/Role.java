package com.msd.utrip.constant.enums;

import lombok.Getter;

@Getter
public enum Role {
  USER("ROLE_USER"),
  AGENCY("ROLE_AGENCY");

  final String role;

  Role(String role) {
    this.role = role;
  }
}
