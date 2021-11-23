package com.github.amobit.bot.command;

import com.github.amobit.bot.service.SendBotMessageService;
import com.github.amobit.db.service.BookService;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.amobit.bot.command.CommandName.*;
import static com.github.amobit.bot.command.CommandName.HELP;

/**
 * Start {@link Command}.
 */
public class FindGenreCommand extends Command {

    public static final String FIND_GENRE_MESSAGE = String.format("✨<b>Доступные Жанры</b>✨\n\n"
                    + "%s - Классическая литература\n"
                    + "%s - Бизнес книги\n"
                    + "%s - Русская литература\n"
                    + "%s - Зарубежная литература\n"
                    + "%s - Научно-познавательная литература\n"
                    + "%s - Фантастика\n"
                    + "%s - Фэнтези\n"
                    + "%s - Детективы\n"
                    + "%s - Детеские книги\n",
            CLASSIC_BOOK.getCommandName(),
            BUSINESS_BOOK.getCommandName(),
            RUSSIAN_BOOK.getCommandName(),
            FOREIGN_BOOK.getCommandName(),
            SCIENTIFIC_BOOK.getCommandName(),
            FANTASTIC_BOOK.getCommandName(),
            FANTASY_BOOK.getCommandName(),
            DETECTIVE_BOOK.getCommandName(),
            CHILDREN_BOOK.getCommandName());

    public FindGenreCommand(SendBotMessageService sendBotMessageService, BookService bookService) {
        super(sendBotMessageService, bookService);
    }

    @Override
    public void execute(Update update) {
        Message message = update.getMessage();
        sendBotMessageService.sendMessage(message.getChatId().toString(), FIND_GENRE_MESSAGE);
    }
}