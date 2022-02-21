package com.example.demo.controller;

import com.example.demo.dto.ExceptionResponse;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.time.Clock;
import java.time.LocalDateTime;
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
    public ResponseEntity<Book> addBook(@RequestBody Book toSave) {
        logger.info("adding new book [{}]", toSave);

      //  return bookService.saveBook(toSave);  // before we change Book to ResponseEntity<Book>
        var newBook = bookService.saveBook(toSave);
        return ResponseEntity.created(URI.create("books/" + newBook.getId()))
                .body(newBook);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        logger.info("deleting book by id: [{}]", id);
            // when delete = true  then -> Resource Code = 204 (-> noContent() )
            // hene delete = false then -> RC = 4xx (

        boolean deleted = bookService.deleteBookById(id);

        ResponseEntity<Void> result = ResponseEntity.notFound().build();
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return result;
     /* version before optimisation
        if(deleted){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
      */
    }
    //Update (replace)
    @PutMapping("/{id}")
    public Book replaceBook(@PathVariable Long id , @RequestBody Book toReplace){
        logger.info("replace book with id: [{}] for new one: [{}]",id, toReplace);

        return bookService.replaceBook(id, toReplace);
    }
    @PatchMapping
    public Book updateBook(@PathVariable("id") Long id,@RequestBody Book toUpdate){
        logger.info("replace book with new attributes: [{}]", toUpdate);

        return bookService.updateBookWithAttributes(id, toUpdate);
    }
  @ExceptionHandler(BookNotFoundException.class)
   public ResponseEntity<ExceptionResponse> handleBookNotFoundException(Exception exception, HttpServletRequest request){
    //public ResponseEntity<?> handleBookNotFoundException(Exception exception){
        logger.warn("some unexpected exception has occurred", exception);

     // return ResponseEntity.badRequest().body(exception.getMessage());
      return ResponseEntity.badRequest().body(
                new ExceptionResponse(LocalDateTime.now(Clock.systemUTC()),
                        HttpStatus.BAD_REQUEST.value(),
                        exception.getClass().getName(),
                        exception.getMessage(),
                        request.getServletPath()
                        )
        );

    }
}
