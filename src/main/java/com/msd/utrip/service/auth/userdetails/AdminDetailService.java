package com.msd.utrip.service.auth.userdetails;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.constant.enums.Role;
import com.msd.utrip.exception.UserNotFoundException;
import com.msd.utrip.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDetailService implements CustomUserDetailsService {
  private final AdminRepository adminRepository;

  @Override
  public UserDetails loadUserByUsername(String login) throws UserNotFoundException {
    return adminRepository
        .findByLogin(login)
        .orElseThrow(() -> new UserNotFoundException(ErrorCode.ADMIN_LOGIN_INVALID));
  }

  @Override
  public Role getSupportedRole() {
    return Role.ADMIN;
  }
}