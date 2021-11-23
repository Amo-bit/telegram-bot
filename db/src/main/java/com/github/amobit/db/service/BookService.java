package com.github.amobit.db.service;

import com.github.amobit.db.model.Book;

public interface BookService {
    Book getBook(long id);
    Book getBook(String author);
}
