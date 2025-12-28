package com.msd.utrip.service.auth;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.dto.request.UserLoginRequest;
import com.msd.utrip.dto.request.UserRegisterRequest;
import com.msd.utrip.dto.response.TokenResponse;
import com.msd.utrip.entity.redis.OtpEntity;
import com.msd.utrip.entity.user.UserEntity;
import com.msd.utrip.exception.PasswordInvalidException;
import com.msd.utrip.exception.UserNotFoundException;
import com.msd.utrip.mapper.UserMapper;
import com.msd.utrip.repository.UserRepository;
import com.msd.utrip.service.auth.otp.OtpService;
import com.msd.utrip.service.auth.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAuthService {
  private final UserRepository userRepository;
  private final TokenService tokenService;
  private final PasswordEncoder passwordEncoder;
  private final OtpService otpService;
  private final UserMapper userMapper;

  @Transactional
  public TokenResponse register(final UserRegisterRequest request) {
    OtpEntity otpEntity = otpService.getAndValidateOtp(request.otpCode(), request.phone());

    UserEntity user = userMapper.mapRequestToUser(request, otpEntity.getTelegramId());

    user.setPassword(passwordEncoder.encode(request.password()));

    user = userRepository.save(user);

    return tokenService.createPair(user);
  }

  public TokenResponse login(final UserLoginRequest request) {
    UserEntity user =
        userRepository
            .findByPhone(request.phone())
            .orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_INVALID_CODE));

    if (!passwordEncoder.matches(request.password(), user.getPassword())) {
      throw new PasswordInvalidException(ErrorCode.PASSWORD_INVALID_CODE);
    }

    return tokenService.createPair(user);
  }
}
