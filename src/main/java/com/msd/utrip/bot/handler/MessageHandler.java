package com.msd.utrip.bot.handler;

import com.msd.utrip.bot.UtripBot;
import com.msd.utrip.constant.enums.BotState;
import com.msd.utrip.service.bot.MessageService;
import com.msd.utrip.service.bot.redis.BotStateService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageHandler  {
  private final BotStateService botStateService;
  private final MessageService messageService;

  public void handleUpdate(final Update update, final UtripBot bot) {
    BotState state = botStateService.getState(update.getMessage().getChatId());

    Message message = update.getMessage();

    if (Objects.requireNonNull(state) == BotState.PHONE_SEND) {
      messageService.processPhone(bot, message);
    }
  }
}
