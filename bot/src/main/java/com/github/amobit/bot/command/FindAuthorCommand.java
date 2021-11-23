package com.github.amobit.bot.command;

import com.github.amobit.bot.service.SendBotMessageService;
import com.github.amobit.db.BookService;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

/**
 * Start {@link Command}.
 */
public class FindAuthorCommand extends Command {

    public final static String FIND_AUTHOR_MESSAGE = "Для поиска книг по автору, напишите имя автора через пробел";

    public FindAuthorCommand(SendBotMessageService sendBotMessageService, BookService bookService) {
        super(sendBotMessageService, bookService);
    }

    @Override
    public void execute(Update update) {
        Message message = update.getMessage();
        String text = message.getText().toLowerCase();
        sendBotMessageService.sendMessage(message.getChatId().toString(), FIND_AUTHOR_MESSAGE);
    }
}