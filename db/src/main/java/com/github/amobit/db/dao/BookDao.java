package com.github.amobit.db.dao;

import com.github.amobit.db.model.Book;

import java.util.Optional;

public interface BookDao {

    Optional<Book> getBookById(long id);

    Optional<Book> getBookByAuthor(String author);
}


