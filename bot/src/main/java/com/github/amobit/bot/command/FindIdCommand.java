package com.github.amobit.bot.command;

import com.github.amobit.bot.service.SendBotMessageService;
import com.github.amobit.db.model.Book;
import com.github.amobit.db.service.BookService;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class FindIdCommand extends Command{

    public final static String FIND_ID_MESSAGE = "Полная информация о запрашиваемой книге: ";

    public FindIdCommand(SendBotMessageService sendBotMessageService, BookService bookService) {
        super(sendBotMessageService, bookService);
    }

    @Override
    public void execute(Update update) {
        Message message = update.getMessage();
        String text = message.getText().toLowerCase();
        Long id = Long.valueOf(text.substring(text.indexOf(" ") + 1));
        sendBotMessageService.sendMessage(message.getChatId().toString(), FIND_ID_MESSAGE);
        Book book = bookService.getBook(id);
        if (book != null) {
            sendBotMessageService.sendMessage(message.getChatId().toString(), bookToString(book));
        }
        else {
            sendBotMessageService.sendMessage(message.getChatId().toString(), "Книги с таким Id не найдено!");
        }
    }
    @Override
    public String bookToString(Book book) {
        Long id = book.getId();
        String title = book.getTitle();
        String author = book.getAuthor();
        String genre = book.getGenre();
        String publisher = book.getPublisher();
        String publication_date = book.getPublication_date();
        String rating = book.getRating();
        String definition = book.getDefinition();
        return  "Id: " + id + '\n' +
                "Название: " + title + '\n' +
                "Автор: " + author + '\n' +
                "Жанр: " + genre + '\n' +
                "Издательсво: " + publisher + '\n' +
                "Дата публикации: " + publication_date + '\n' +
                "Рейтинг: " + rating  + '\n' +
                "Описание: " + definition;
    }
}
