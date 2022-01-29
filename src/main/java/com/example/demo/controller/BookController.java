package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {

        logger.info("getAllBooks()");

        return bookService.findAllBooks();
    }
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") Long id) {
        logger.info("find book by id [{}]", id);

        return bookService.finfBooksById(id);
    }
    @PostMapping
    public Book addBook(@RequestBody Book toSave) {
        logger.info("adding new book [{}]", toSave);

        return bookService.saveBook(toSave);
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        logger.info("deleting book by id: [{}]", id);

        bookService.deleteBookBy(id);
    }
    //Update (replace)
    @PutMapping
    public Book replaceBook(@PathVariable Long id , @RequestBody Book toReplace){
        logger.info("replace book with id: [{}] for new one: [{}]",id, toReplace);

        return bookService.replaceBook(id, toReplace);
    }
    @PatchMapping
    public Book updateBook(@PathVariable("id") Long id,@RequestBody Book toUpdate){
        logger.info("replace book with new attributes: [{}]", toUpdate);

        return bookService.updateBookWithAttributes(id, toUpdate);
    }
}
