package com.msd.utrip.bot.handler.keyboard;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

@Component
@RequiredArgsConstructor
public class MainKeyboards {

  public ReplyKeyboardMarkup phoneKeyboard() {
    KeyboardRow rowOne = new KeyboardRow();

    var button = new KeyboardButton("Send phone number");
    button.setRequestContact(true);

    rowOne.add(button);

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(List.of(rowOne));
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboard(true);

    return replyKeyboardMarkup;
  }

  public InlineKeyboardMarkup otpRenewKeyboard() {
    InlineKeyboardButton button =
        InlineKeyboardButton.builder().text("Новый код").callbackData("new_code").build();

    InlineKeyboardRow row = new InlineKeyboardRow(List.of(button));

    return InlineKeyboardMarkup.builder().keyboard(List.of(row)).build();
  }
}
