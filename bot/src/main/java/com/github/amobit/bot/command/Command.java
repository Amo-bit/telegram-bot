package com.github.amobit.bot.command;


import com.github.amobit.bot.service.SendBotMessageService;
import com.github.amobit.db.service.BookService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Command abstract for handling telegram-bot commands.
 */
public abstract class Command {

    protected final SendBotMessageService sendBotMessageService;
    protected final BookService bookService;

    public Command(SendBotMessageService sendBotMessageService, BookService bookService) {
        this.sendBotMessageService = sendBotMessageService;
        this.bookService = bookService;
    }

    public Command(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
        this.bookService = null;
    }

    /**
     * Main method, which is executing command logic.
     *
     * @param update provided {@link Update} object with all the needed data for command.
     */
    abstract public void execute(Update update);
}