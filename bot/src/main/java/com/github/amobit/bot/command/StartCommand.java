package com.github.amobit.bot.command;

import com.github.amobit.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Start {@link Command}.
 */
public class StartCommand extends Command {

    public final static String START_MESSAGE = "Привет. Я Search New Book Telegram Bot. Я помогу тебе подобрать " +
            "книгу для прочтения и узнать какие есть книги у интересующего автора. Чтобы узнать что я умею " +
            "выбери команду /help";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        super(sendBotMessageService);
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}