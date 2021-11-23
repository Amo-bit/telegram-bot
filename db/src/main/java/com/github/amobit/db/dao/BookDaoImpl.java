package com.github.amobit.db.dao;

import com.github.amobit.db.model.Book;
import com.github.amobit.db.service.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.sql.Types.VARCHAR;

@Repository
public class BookDaoImpl implements BookDao {

    private static final String SQL_GET_BOOK_BY_ID =
            "select id, title, author, genre, publisher, publication_date, definition, rating from book where id = :id";
    private static final String SQL_GET_BOOK_BY_AUTHOR =
            "select id, title, author, genre, publisher, publication_date, definition, rating from book where lower(author) like :author";
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
    public List<Book> getBookByAuthor(String author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author", "%" + author.toLowerCase() + "%");
        try {
            return jdbcTemplate.query(
                            SQL_GET_BOOK_BY_AUTHOR,
                            params,
                            bookMapper
            );
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Empty list");
            return Collections.emptyList();
        }
    }
}
