package com.msd.utrip.repository;

import com.msd.utrip.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByPhone(String phone);

    Optional<UserEntity> findByTelegramId(Long telegramId);

}
