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

    private final BookCrudRepository bookCrudRepository;

    public BookRepository(BookCrudRepository bookCrudRepository) {
        this.bookCrudRepository = bookCrudRepository;
    }

    /*   // lista, ktora wykozytywalismy zanim wdrozylismy DB i interface BoookCrudRepository (L13 5 min. Screen Recording (14-03-2021 11-40-45)
    private List<Book> books;

    public BookRepository() {
        List<Book> someBooks = List.of(
                new Book(1L,"Henryk", "Sienkiewicz","Potop"),
                new Book(2L,"Henryk", "Sienkiewicz","Pan Wolodyjowski"),
                new Book(3L,"Henryk", "Sienkiewicz","Quo Vadis")
        );
        this.books = new ArrayList<>(someBooks);
        logger.info("book repository initialized wit books {}", books);
    }
*/
    public List<Book> findAllBooks() {
      /*  var result = new ArrayList<Book>();
        bookCrudRepository.findAll()
                .forEach(book -> result.add(book)); */     // we change it for one  line below
        var result = bookCrudRepository.findAllBooks();

        logger.info("Number of found books: [{}]",result.size());
        logger.info("Found books: {}",result);
        return result;
    }

    public boolean deleteBookWithId(Long id) {
       // return books.removeIf(book -> book.getId().equals(id)); // version before CrudRepository implementation

        boolean exists = bookCrudRepository.existsById(id);
        if (exists) {
            bookCrudRepository.deleteById(id);
        }
        return exists;
    }
}
