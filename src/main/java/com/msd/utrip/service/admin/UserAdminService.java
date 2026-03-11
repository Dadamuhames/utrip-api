package com.msd.utrip.service.admin;

import com.msd.utrip.dto.response.admin.UserAdminResponse;
import com.msd.utrip.entity.user.UserEntity;
import com.msd.utrip.exception.EntityNotFoundException;
import com.msd.utrip.mapper.UserMapper;
import com.msd.utrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAdminService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Transactional(readOnly = true)
  public Page<UserAdminResponse> list(Pageable pageable) {

    Page<UserEntity> users = userRepository.findAll(pageable);

    return users.map(userMapper::entityToAdminResponse);
  }

  private UserEntity getInstance(Long id) {
    return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  @Transactional(readOnly = true)
  public UserAdminResponse getOne(Long id) {

    UserEntity entity = getInstance(id);

    return userMapper.entityToAdminResponse(entity);
  }
}
