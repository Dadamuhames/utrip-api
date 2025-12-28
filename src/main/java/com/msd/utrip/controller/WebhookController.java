package com.msd.utrip.controller;

import com.msd.utrip.bot.UtripBot;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/webhook/telegram")
public class WebhookController {
  private final UtripBot utripBot;

  @PostMapping
  public BotApiMethod<?> onUpdateReceived(@RequestBody final Update update) {
    return utripBot.consumeUpdate(update);
  }
}
