package com.github.amobit.bot.command;

import com.github.amobit.bot.service.SendBotMessageService;
import com.github.amobit.db.model.Book;
import com.github.amobit.db.service.BookService;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

/**
 * Start {@link Command}.
 */
public class FindAuthorCommand extends Command {

    public final static String FIND_AUTHOR_MESSAGE = "✨<b>Для получения подробной информации о книге, " +
            "выполните поиск по ID</b>✨\n\n" + "Список книг автора: ";

    public FindAuthorCommand(SendBotMessageService sendBotMessageService, BookService bookService) {
        super(sendBotMessageService, bookService);
    }

    @Override
    public void execute(Update update) {
        Message message = update.getMessage();
        String text = message.getText().toLowerCase();
        String author = text.substring(text.indexOf(" ") + 1);
        sendBotMessageService.sendMessage(message.getChatId().toString(), FIND_AUTHOR_MESSAGE);
        List<Book> books = bookService.getBook(author);
        List<Book> sortedBooks = books.subList(0, Math.min(books.size(), 10));
        if (sortedBooks.size() != 0) {
            for (Book book : sortedBooks) {
                sendBotMessageService.sendMessage(message.getChatId().toString(), bookToString(book));
            }
        }
        else {
            sendBotMessageService.sendMessage(message.getChatId().toString(),
                    "Книг данного автора не найдено!");
        }
    }
}