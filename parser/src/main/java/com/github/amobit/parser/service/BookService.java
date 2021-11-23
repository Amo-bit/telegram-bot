package com.github.amobit.parser.service;

import com.github.amobit.parser.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    void save(Book book);
    boolean isExist(String bookTitle);
    List<Book> getAllBooks();
}
