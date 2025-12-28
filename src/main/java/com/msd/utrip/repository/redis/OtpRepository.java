package com.msd.utrip.repository.redis;

import com.msd.utrip.entity.redis.OtpEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OtpRepository extends CrudRepository<OtpEntity, Long> {

  Optional<OtpEntity> findByPhone(String phone);
}
