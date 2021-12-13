package com.example.demo.service;

import com.example.demo.exception.BookNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks(){
        var result = bookRepository.findAllBooks();

        logger.info("number of found books: [{}] ", result.size());
        logger.debug("result: {}", result);
        return  result; //Collections.emptyList();
    }

    public Book finfBooksById(Long id) {
        Objects.requireNonNull(id,"");
        var result = bookRepository.findAllBooks()
                .stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(String.format("No book with id:[%]",id)));
        logger.info("Book found for id: [{}] is [{}]", id,result );
        return result;
    }

    public Book saveBook(Book toSave) {
        //find max id
        //add book with id(max id +1)
        // return book with id

         Long currentMaxId = bookRepository.findAllBooks()
                .stream()
                .mapToLong(value -> value.getId())
                .max()
                 .orElse(1);
         toSave.setId(currentMaxId +1);
         bookRepository.findAllBooks().add(toSave);

         logger.info("saved book [{}]", toSave);
        return toSave;
    }
}
