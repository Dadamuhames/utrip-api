package com.msd.utrip.entity.redis;

import java.io.Serializable;

import com.msd.utrip.constant.enums.BotState;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@RedisHash("botState")
public class BotStateEntity implements Serializable {
  @Id private Long telegramChatId;

  private BotState state;
}
