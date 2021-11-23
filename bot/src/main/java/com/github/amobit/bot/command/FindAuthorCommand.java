package com.github.amobit.bot.command;

import com.github.amobit.bot.service.SendBotMessageService;
import com.github.amobit.db.service.BookService;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Start {@link Command}.
 */
public class FindAuthorCommand extends Command {

    public final static String FIND_AUTHOR_MESSAGE = "Список книг данного автора: ";

    public FindAuthorCommand(SendBotMessageService sendBotMessageService, BookService bookService) {
        super(sendBotMessageService, bookService);
    }

    @Override
    public void execute(Update update) {
        Message message = update.getMessage();
        String text = message.getText().toLowerCase();
        String id = text.substring(text.indexOf(" ") + 1);
        sendBotMessageService.sendMessage(message.getChatId().toString(), FIND_AUTHOR_MESSAGE);
        if (bookService.getBook(1750) != null) {
            sendBotMessageService.sendMessage(message.getChatId().toString(), String.valueOf(bookService.getBook(1750)));
        }
        else {
            sendBotMessageService.sendMessage(message.getChatId().toString(), "Книг данного автора нет.");
        }
    }
}