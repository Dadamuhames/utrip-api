package com.msd.utrip.service.bot.redis;

import com.msd.utrip.constant.enums.BotState;
import com.msd.utrip.entity.redis.BotStateEntity;
import com.msd.utrip.repository.redis.BotStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BotStateService {
  private final BotStateRepository botStateRepository;

  public BotStateEntity getInstance(final Long chatId) {
    BotStateEntity userState = botStateRepository.findById(chatId).orElse(null);

    if (userState == null) {
      userState = BotStateEntity.builder().telegramChatId(chatId).state(BotState.PHONE_SEND).build();
      return botStateRepository.save(userState);
    }

    return userState;
  }

  public BotState getState(final Long chatId) {
    BotStateEntity userState = getInstance(chatId);

    return userState.getState();
  }

  public void setState(final Long chatId, final BotState state) {
    BotStateEntity userState = getInstance(chatId);

    userState.setState(state);

    botStateRepository.save(userState);
  }
}
