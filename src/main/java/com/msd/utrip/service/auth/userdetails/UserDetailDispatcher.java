package com.msd.utrip.service.auth.userdetails;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.constant.enums.Role;
import com.msd.utrip.exception.RoleNotSupportedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserDetailDispatcher {
  private final Map<Role, CustomUserDetailsService> customUserDetailServices;

  public UserDetails loadUserByLoginAndRole(String login, Role role)
      throws RoleNotSupportedException {

    CustomUserDetailsService userDetailsService = customUserDetailServices.get(role);

    if (userDetailsService == null) {
      throw new RoleNotSupportedException(ErrorCode.ROLE_NOT_SUPPORTED_CODE);
    }

    return userDetailsService.loadUserByUsername(login);
  }
}
