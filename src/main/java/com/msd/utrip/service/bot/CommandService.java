package com.msd.utrip.service.bot;

import com.msd.utrip.bot.UtripBot;
import com.msd.utrip.bot.handler.keyboard.MainKeyboards;
import com.msd.utrip.constant.enums.BotState;
import com.msd.utrip.entity.user.UserEntity;
import com.msd.utrip.service.auth.otp.OtpService;
import com.msd.utrip.service.bot.redis.BotStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.message.Message;

@Service
@RequiredArgsConstructor
public class CommandService {
  private final BotStateService botStateService;
  private final MainKeyboards mainKeyboards;
  private final OtpService otpService;

  public void handleStart(final UtripBot utripBot, final Message message, final UserEntity user) {
    Long chatId = message.getChatId();

    String otp = otpService.createOtp(chatId, user.getPhone());

    utripBot.sendMessage(chatId, String.format("Your code: <code>%s</code>", otp));
  }
}
