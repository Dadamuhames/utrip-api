package com.msd.utrip.service.bot;

import com.msd.utrip.bot.UtripBot;
import com.msd.utrip.bot.handler.keyboard.MainKeyboards;
import com.msd.utrip.constant.enums.BotState;
import com.msd.utrip.service.auth.otp.OtpService;
import com.msd.utrip.service.bot.redis.BotStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.message.Message;

@Service
@RequiredArgsConstructor
public class MessageService {
  private final BotStateService botStateService;
  private final OtpService otpService;
  private final MainKeyboards mainKeyboards;

  public void processPhone(final UtripBot utripBot, final Message message) {
    Long chatId = message.getChatId();

    if (!message.hasContact()) {
      utripBot.sendMessage(chatId, "Отправьте свой номер телефона", mainKeyboards.phoneKeyboard());
      return;
    }

    String otp = otpService.createOtp(chatId, message.getContact().getPhoneNumber());

    utripBot.sendMessage(chatId, String.format("Your code: <code>%s</code>", otp));

    botStateService.setState(chatId, BotState.IDLE);
  }
}
