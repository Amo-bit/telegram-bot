package com.github.amobit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book getBook(long id) {
        return bookDao.getBookById(id)
                .orElseThrow(() -> new RuntimeException(String.valueOf(id)));
    }
}

