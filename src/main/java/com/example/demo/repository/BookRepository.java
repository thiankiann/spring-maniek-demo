package com.example.demo.repository;

import com.example.demo.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class BookRepository {

    private static final Logger logger = LoggerFactory.getLogger(BookRepository.class);
    private List<Book> books;

    public BookRepository() {
        List<Book> someBooks = List.of(
                new Book(1L,"Henryk Sienkiewicz","Potop"),
                new Book(2L,"Henryk Sienkiewicz","Pan Wolodyjowski"),
                new Book(3L,"Henryk Sienkiewicz","Quo Vadis")
        );
        this.books = new ArrayList<>(someBooks);
        logger.info("book repository initialized wit books {}", books);
    }

    public List<Book> findAllBooks() {
        return books;
    }
}
