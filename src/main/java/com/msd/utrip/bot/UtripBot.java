package com.msd.utrip.bot;

import com.msd.utrip.bot.handler.MainHandler;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.DeleteWebhook;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class UtripBot extends BaseBot {
  @Autowired public SetWebhook setWebhook;
  @Autowired public MainHandler mainHandler;

  public UtripBot(@Qualifier("botToken") String botToken) {
    super(botToken);
  }

  @Override
  public void runDeleteWebhook() {
    executeMethod(new DeleteWebhook());
  }

  @Override
  public void runSetWebhook() {
    executeMethod(setWebhook);
  }

  @PostConstruct
  public void initWebhook() {
    log.info("Registering Telegram webhook...");
    runDeleteWebhook();
    runSetWebhook();
  }

  @Override
  public BotApiMethod<?> consumeUpdate(Update update) {
    mainHandler.handleUpdate(update, this);

    return null;
  }

  @Override
  public String getBotPath() {
    return "/api/v1/webhook/telegram";
  }
}
