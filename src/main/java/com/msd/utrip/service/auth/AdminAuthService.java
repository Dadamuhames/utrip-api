package com.msd.utrip.service.auth;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.dto.request.agency.LoginRequest;
import com.msd.utrip.dto.response.TokenResponse;
import com.msd.utrip.entity.AdminEntity;
import com.msd.utrip.exception.PasswordInvalidException;
import com.msd.utrip.exception.UserNotFoundException;
import com.msd.utrip.repository.AdminRepository;
import com.msd.utrip.service.auth.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminAuthService {

  private final AdminRepository adminRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenService tokenService;

  public TokenResponse login(LoginRequest request) {
    AdminEntity admin =
        adminRepository
            .findByLogin(request.login())
            .orElseThrow(() -> new UserNotFoundException(ErrorCode.LOGIN_INVALID_CODE));

    if (!passwordEncoder.matches(request.password(), admin.getPassword())) {
      throw new PasswordInvalidException(ErrorCode.PASSWORD_INVALID_CODE);
    }

    return tokenService.createPair(admin);
  }
}
