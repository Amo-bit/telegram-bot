package com.github.amobit.db.service;

import com.github.amobit.db.model.Book;

import java.util.List;

public interface BookService {
    Book getBook(long id);
    List<Book> getBook(String author);
    List<Book> getBookByTitle(String title);
    List<Book> getBookByGenre(String genre);
}
