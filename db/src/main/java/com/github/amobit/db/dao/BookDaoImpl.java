package com.github.amobit.db.dao;

import com.github.amobit.db.model.Book;
import com.github.amobit.db.service.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    private static final String SQL_GET_BOOK_BY_ID =
            "select id, title, author, genre, publisher, publication_date, definition, rating from book where id = :id";
    private static final String SQL_GET_BOOK_BY_AUTHOR =
            "select id, title, author, genre, publisher, publication_date, definition, rating " +
                    "from book where author = :author";
    private final BookMapper bookMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoImpl(BookMapper bookMapper, NamedParameterJdbcTemplate jdbcTemplate) {
        this.bookMapper = bookMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Book> getBookById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            SQL_GET_BOOK_BY_ID,
                            params,
                            bookMapper
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    @Override
    public Optional<Book> getBookByAuthor(String author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author", author);
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            SQL_GET_BOOK_BY_AUTHOR,
                            params,
                            bookMapper
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
