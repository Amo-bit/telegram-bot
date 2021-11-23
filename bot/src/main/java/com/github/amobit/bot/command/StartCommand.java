package com.github.amobit.bot.command;

import com.github.amobit.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Start {@link Command}.
 */
public class StartCommand extends Command {

    public final static String START_MESSAGE = "Привет. Я Search New Book Telegram Bot. Я помогу тебе быть в курсе последних " +
            "книг тех авторов, которые тебе интересны. Я еще маленький и только учусь.";

    // Здесь не добавляем сервис через получение из Application Context.
    // Потому что если это сделать так, то будет циклическая зависимость, которая
    // ломает работу приложения.
    public StartCommand(SendBotMessageService sendBotMessageService) {
        super(sendBotMessageService);
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}