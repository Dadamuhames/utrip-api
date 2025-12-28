package com.msd.utrip.service.auth.userdetails;

import com.msd.utrip.constant.enums.Role;
import com.msd.utrip.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetailsService {
  UserDetails loadUserByUsername(final String login) throws UserNotFoundException;

  Role getSupportedRole();
}
