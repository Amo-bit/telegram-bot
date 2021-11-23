package com.github.amobit.bot.command.genge;

import com.github.amobit.bot.command.Command;
import com.github.amobit.bot.service.SendBotMessageService;
import com.github.amobit.db.model.Book;
import com.github.amobit.db.service.BookService;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class ChildrenBookCommand extends Command {
    public final static String genre = "Детские-книги";

    public final static String GENRE_MESSAGE = "✨<b>Для получения подробной информации о книге, " +
            "выполните поиск по ID</b>✨\n\n" + "Список книг по жанру " + genre + ": ";

    public ChildrenBookCommand(SendBotMessageService sendBotMessageService, BookService bookService) {
        super(sendBotMessageService, bookService);
    }


    @Override
    public void execute(Update update) {
        Message message = update.getMessage();
        sendBotMessageService.sendMessage(message.getChatId().toString(), GENRE_MESSAGE);
        List<Book> books = bookService.getBookByGenre(genre);
        List<Book> sortedBooks = books.subList(0, Math.min(books.size(), 10));
        if (sortedBooks.size() != 0) {
            for (Book book : sortedBooks) {
                sendBotMessageService.sendMessage(message.getChatId().toString(), bookToString(book));
            }
        }
        else {
            sendBotMessageService.sendMessage(message.getChatId().toString(),
                    "Книг данного жанра не найдено!");
        }
    }
}
