package com.msd.utrip.service.profile;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.dto.request.UserProfileRequest;
import com.msd.utrip.dto.response.UserProfileResponse;
import com.msd.utrip.dto.response.UserResponse;
import com.msd.utrip.entity.user.UserEntity;
import com.msd.utrip.exception.PasswordInvalidException;
import com.msd.utrip.mapper.UserMapper;
import com.msd.utrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;

  public UserProfileResponse getProfile(final UserEntity user) {
    return userMapper.userToProfile(user);
  }

  public void updateProfile(final UserEntity user, final UserProfileRequest request) {
    userMapper.updateProfile(request, user);

    if (request.image() != null) {
      user.setImage(request.image());
    }

    if (request.newPassword() != null) {
      if (!passwordEncoder.matches(request.password(), user.getPassword())) {
        throw new PasswordInvalidException(ErrorCode.PASSWORD_INVALID_CODE);
      }

      user.setPassword(request.newPassword());
    }

    userRepository.save(user);
  }
}
