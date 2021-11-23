package com.github.amobit.db;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setAuthor(resultSet.getString("author"));
        book.setPublisher(resultSet.getString("publisher"));
        book.setPublication_date(resultSet.getString("publication_date"));
        book.setGenre(resultSet.getString("genre"));
        book.setDefinition(resultSet.getString("definition"));
        book.setRating(resultSet.getString("rating"));
        return book;
    }
}
