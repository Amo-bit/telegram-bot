package com.github.amobit.bot.command;

import com.github.amobit.bot.service.SendBotMessageService;
import com.github.amobit.db.service.BookService;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Start {@link Command}.
 */
public class TopCommand extends Command {

    public final static String FIND_AUTHOR_MESSAGE = "Список книг по данному автору: ";

    public TopCommand(SendBotMessageService sendBotMessageService, BookService bookService) {
        super(sendBotMessageService, bookService);
    }

    @Override
    public void execute(Update update) {
        Message message = update.getMessage();
        sendBotMessageService.sendMessage(message.getChatId().toString(), FIND_AUTHOR_MESSAGE);
    }
}