package com.msd.utrip.bot.handler;

import com.msd.utrip.bot.UtripBot;
import com.msd.utrip.entity.user.UserEntity;
import com.msd.utrip.service.bot.CommandService;
import com.msd.utrip.service.bot.redis.BotStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CommandHandler {
  private final BotStateService botStateService;
  private final CommandService commandService;

  public void handleUpdate(final Update update, final UtripBot bot, final UserEntity user) {
    String message = update.getMessage().getText();

    switch (message) {
      case "/start" -> commandService.handleStart(bot, update.getMessage(), user);

      default -> {}
    }
  }
}
