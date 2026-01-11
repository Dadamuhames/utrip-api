package com.msd.utrip.entity.user;

import com.msd.utrip.constant.enums.Gender;
import com.msd.utrip.constant.enums.Role;
import com.msd.utrip.entity.base.BaseDeactivatableEntity;
import com.msd.utrip.entity.base.CustomUserDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity extends BaseDeactivatableEntity implements CustomUserDetails {
  @Column(nullable = false)
  private String fullName;

  @Column(nullable = false)
  private String phone;

  private String image;

  @Column(nullable = false)
  private Long telegramId;

  @Column(nullable = false)
  private String password;

  @Override
  public Role getUserRole() {
    return Role.USER;
  }

  @Override
  public String getUsername() {
    return phone;
  }
}
