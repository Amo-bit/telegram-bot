package com.github.amobit.db;

import java.util.Optional;

public interface BookDao {

    Optional<Book> getBookById(long id);
}


