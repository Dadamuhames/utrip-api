package com.msd.utrip.entity.redis;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@RedisHash(value = "savedTours")
public class SavedTourEntity {
  @Id private Long id;

  @Indexed private Long userId;

  private Long tourId;

  private LocalDateTime createdAt;
}
