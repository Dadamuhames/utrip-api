package com.msd.utrip.service.auth;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.dto.request.AgencyLoginRequest;
import com.msd.utrip.dto.response.TokenResponse;
import com.msd.utrip.entity.agency.AgencyEntity;
import com.msd.utrip.exception.PasswordInvalidException;
import com.msd.utrip.exception.UserNotFoundException;
import com.msd.utrip.repository.agency.AgencyRepository;
import com.msd.utrip.service.auth.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AgencyAuthService {
  private final AgencyRepository agencyRepository;
  private final TokenService tokenService;
  private final PasswordEncoder passwordEncoder;


  @Transactional
  public TokenResponse login(final AgencyLoginRequest request) {
    AgencyEntity agency =
        agencyRepository
            .findByLogin(request.login())
            .orElseThrow(() -> new UserNotFoundException(ErrorCode.LOGIN_INVALID_CODE));

    if (!passwordEncoder.matches(request.password(), agency.getPassword())) {
      throw new PasswordInvalidException(ErrorCode.PASSWORD_INVALID_CODE);
    }

    return tokenService.createPair(agency);
  }
}
