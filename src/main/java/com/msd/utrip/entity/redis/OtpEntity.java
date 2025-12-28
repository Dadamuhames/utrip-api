package com.msd.utrip.entity.redis;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@AllArgsConstructor
@RedisHash(value = "otpEntity", timeToLive = 3600)
public class OtpEntity {
  @Id private Long id;

  @Indexed private Long telegramId;

  @Indexed private String phone;

  private String otpHash;
}
