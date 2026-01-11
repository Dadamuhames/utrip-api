package com.msd.utrip.service.auth.userdetails;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.constant.enums.Role;
import com.msd.utrip.exception.UserNotFoundException;
import com.msd.utrip.repository.agency.AgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgencyDetailService implements CustomUserDetailsService {
  private final AgencyRepository agencyRepository;

  @Override
  public UserDetails loadUserByUsername(String login) throws UserNotFoundException {
    return agencyRepository
        .findByLogin(login)
        .orElseThrow(() -> new UserNotFoundException(ErrorCode.LOGIN_INVALID_CODE));
  }

  @Override
  public Role getSupportedRole() {
    return Role.AGENCY;
  }
}
