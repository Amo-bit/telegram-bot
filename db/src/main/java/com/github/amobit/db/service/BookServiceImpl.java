package com.github.amobit.db.service;

import com.github.amobit.db.model.Book;
import com.github.amobit.db.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElse(null);
    }

    @Override
    public List<Book> getBook(String author) {
        return bookDao.getBookByAuthor(author);
    }
}

