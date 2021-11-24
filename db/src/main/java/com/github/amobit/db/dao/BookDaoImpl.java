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

@Repository
public class BookDaoImpl implements BookDao {

    private static final String SQL_GET_BOOK_BY_ID =
            "select id, title, author, genre, publisher, publication_date, definition, " +
                    "rating from book where id = :id";
    private static final String SQL_GET_BOOK_BY_AUTHOR =
            "select id, title, author, genre, publisher, publication_date, definition," +
                    " rating from book where lower(author) like :author Order By rating DESC";
    private static final String SQL_GET_BOOK_BY_TITLE =
            "select id, title, author, genre, publisher, publication_date, definition," +
                    " rating from book where lower(title) like :title Order By rating DESC";
    private static final String SQL_GET_BOOK_BY_GENRE =
            "select id, title, author, genre, publisher, publication_date, definition," +
                    " rating from book where lower(genre) like :genre Order By rating DESC";

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
            return Optional.ofNullable(null);
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

    @Override
    public List<Book> getBookTitle(String title) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", "%" + title.toLowerCase() + "%");
        try {
            return jdbcTemplate.query(
                    SQL_GET_BOOK_BY_TITLE,
                    params,
                    bookMapper
            );
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Empty list");
            return Collections.emptyList();
        }
    }

    @Override
    public List<Book> getBookGenre(String genre) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("genre", "%" + genre.toLowerCase() + "%");
        try {
            return jdbcTemplate.query(
                    SQL_GET_BOOK_BY_GENRE,
                    params,
                    bookMapper
            );
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Empty list");
            return Collections.emptyList();
        }
    }
}
