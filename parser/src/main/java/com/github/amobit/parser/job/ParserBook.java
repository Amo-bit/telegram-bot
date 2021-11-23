package com.github.amobit.parser.job;

import com.github.amobit.parser.entity.Book;
import com.github.amobit.parser.service.BookService;
import com.github.amobit.parser.util.Genre;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
public class ParserBook {

    @Autowired
    BookService bookService;

    @Scheduled(fixedDelay = 20000)
    public void parseNewBooks() throws UnsupportedEncodingException {
        for (Genre genre : Genre.values()) {
            for (int i = 0; i < 10; i++) {
                int pageNum = i + 1;
                String genreUri = URLEncoder.encode(genre.getGenreName(), "UTF-8");
                String url = "https://www.livelib.ru/genre/" + genreUri + "/listview/biglist/~" + pageNum;
                dataDistributor(url, genre.getGenreName());
            }
        }
    }

    private void dataDistributor(String url, String genreName) {
        try {
            Document document = Jsoup.connect(url)
                    .userAgent("Google Chrome/95.0.4638.69")
                    .referrer("http://www.google.com")
                    .get();
            Elements books = document.select("div[class=brow-inner rback]");

            for (Element element : books){
                String title = element.select("a[class=brow-book-name with-cycle]").text();
                String author = element.select("a[class=brow-book-author]").text();
                //Find Date
                Elements publicationDateTable = element.select("table[class=compact]");
                Elements rows = publicationDateTable.select("tr");// разбиваем нашу таблицу на строки по тегу
                String publicationDate = null;

                for (int i = 1; i < rows.size(); i++) {
                    Element row = rows.get(1); //по номеру индекса получает строку
                    Elements cols = row.select("td");
                    publicationDate = cols.get(1).text();
                }

                String publisher = element.select("span[itemprop=publisher]").text();
                String definition = element.select("div[class=brow-marg]").text();
                String rating = element.select("span[class=rating-value stars-color-orange]").text();

                if (!bookService.isExist(title)) {
                    Book book = new Book();
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setPublication_date(publicationDate);
                    book.setPublisher(publisher);
                    book.setDefinition(definition);
                    book.setRating(rating);
                    book.setGenre(genreName);
                    bookService.save(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

