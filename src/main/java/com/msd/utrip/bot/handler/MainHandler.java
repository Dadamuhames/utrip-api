package com.msd.utrip.bot.handler;

import com.msd.utrip.bot.UtripBot;
import com.msd.utrip.bot.handler.keyboard.MainKeyboards;
import com.msd.utrip.constant.enums.BotState;
import com.msd.utrip.entity.user.UserEntity;
import com.msd.utrip.repository.UserRepository;
import com.msd.utrip.service.bot.redis.BotStateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;

@Slf4j
@Component
@RequiredArgsConstructor
public class MainHandler {
  private final BotStateService botStateService;
  private final UserRepository userRepository;
  private final MessageHandler messageHandler;
  private final CommandHandler commandHandler;
  private final MainKeyboards mainKeyboards;

  public void handleUpdate(final Update update, final UtripBot bot) {
    Message message = update.getMessage();

    Long chatId =
        message != null ? message.getChatId() : update.getCallbackQuery().getMessage().getChatId();

    UserEntity user = userRepository.findByTelegramId(chatId).orElse(null);

    BotState state = botStateService.getState(chatId);

    if ((user == null && !state.equals(BotState.PHONE_SEND))
        || (user == null && (message == null || !message.hasContact()))) {
      botStateService.setState(chatId, BotState.PHONE_SEND);
      bot.sendMessage(chatId, "Отправьте свой номер телефона", mainKeyboards.phoneKeyboard());
      return;
    }

    if (message != null && message.isCommand()) {
      commandHandler.handleUpdate(update, bot, user);
    } else if (update.hasMessage()) {
      messageHandler.handleUpdate(update, bot);
    }
  }
}
