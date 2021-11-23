package com.github.amobit.parser.service;

import com.github.amobit.parser.entity.Book;
import com.github.amobit.parser.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Autowired
    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Book book) {
        repository.save(book);
    }

    @Override
    public boolean isExist(String topBookTitle) {
        List<Book> books = repository.findAll();
        for (Book book : books){
            if (book.getTitle().equals(topBookTitle)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }
}

