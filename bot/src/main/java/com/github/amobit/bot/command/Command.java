package com.github.amobit.bot.command;


import com.github.amobit.bot.service.SendBotMessageService;
import com.github.amobit.db.model.Book;
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

    public String bookToString(Book book) {
        String title = book.getTitle();
        String author = book.getAuthor();
        String genre = book.getGenre();
        String publication_date = book.getPublication_date();
        String rating = book.getRating();
        return "Название: " + title + '\n' +
                "Автор: " + author + '\n' +
                "Жанр: " + genre + '\n' +
                "Дата публикации: " + publication_date + '\n' +
                "Рейтинг: " + rating;
    }

    /**
     * Main method, which is executing command logic.
     *
     * @param update provided {@link Update} object with all the needed data for command.
     */
    abstract public void execute(Update update);
}