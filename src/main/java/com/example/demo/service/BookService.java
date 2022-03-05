package com.example.demo.service;

import com.example.demo.converter.BookMapper;
import com.example.demo.dto.BookDto;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

@Service
public class BookService {


    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> findAllBooks(){

        var result = bookRepository.findAllBooks()
                .stream()
                .map(book -> bookMapper.fromEntityToDto(book))
                .collect(toList());

        logger.info("number of found books: [{}] ", result.size());
        logger.debug("result: {}", result);
        return  result; //List


     /*   // v. before dto
       var result = bookRepository.findAllBooks();

        logger.info("number of found books: [{}] ", result.size());
        logger.debug("result: {}", result);
        return  result; //Collections.emptyList();
      */
    }

    public BookDto finfBooksById(Long id) {
        Objects.requireNonNull(id,"");
        var result = bookMapper.fromEntityToDto(findBookByIdFromRepository(id));
        logger.info("Book found for id: [{}] is [{}]", id,result );
        return result;
    }

    public Book findBookByIdFromRepository(Long id) {
        return bookRepository.findAllBooks()
                .stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(String.format("No book with id:[%d]", id)));
    }

    public BookDto saveBook(BookDto toSave) {
        //find max id
        //add book with id(max id +1)
        // return book with id

         Long currentMaxId = bookRepository.findAllBooks()
                .stream()
                .mapToLong(value -> value.getId())
                .max()
                 .orElse(1);
         Book entityToSave = bookMapper.fromDtoToEntity(toSave);
         entityToSave.setId(currentMaxId +1);
         bookRepository.findAllBooks().add(entityToSave);

         logger.info("saved book [{}]", entityToSave);
        return bookMapper.fromEntityToDto(entityToSave);
    }

    public boolean deleteBookById(Long id) {
        boolean result = bookRepository.deleteBookWithId(id);
        logger.info("trying to delete book with id: [{}],  [{}]",id , result);
        return result;
    }


    public BookDto replaceBook(Long id, BookDto toReplace) {

        Book oldBook = findBookByIdFromRepository(id);
        Book newBook = bookMapper.fromDtoToEntity(toReplace);

        newBook.setId(id);
        bookRepository.findAllBooks().removeIf(book1 -> book1.getId().equals(id));
        bookRepository.findAllBooks().add(newBook);

        logger.info("replace book [{}] with new one [{}] ", oldBook, toReplace );

        return bookMapper.fromEntityToDto(newBook);

        /** Optional<Book> foundBook = bookRepository
                .findAllBooks()
                .stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
        */
        /** //refactoring 2 (added dto)

        Book book = findBookByIdFromRepository(id);

        toReplace.setId(id);
        bookRepository.findAllBooks().removeIf(book1 -> book1.getId().equals(id));
        bookRepository.findAllBooks().add(toReplace);

        logger.info("replace book [{}] with new one [{}] ", book, toReplace );

        return toReplace;
         */
    }

    public BookDto updateBookWithAttributes(Long id, BookDto toUpdate) {

        Book bookEntityToUpdate = bookMapper.fromDtoToEntity(toUpdate);
        Book book = findBookByIdFromRepository(id);

        if(nonNull(bookEntityToUpdate.getName())){
            book.setName(bookEntityToUpdate.getName());
        }
        if(nonNull(bookEntityToUpdate.getSurname())){
            book.setName(bookEntityToUpdate.getSurname());
        }
        if(nonNull(toUpdate.getTitle())){
            book.setTitle(toUpdate.getTitle());
        }

        logger.info("updated book: [{}], with changes to apply: [{}]", book, toUpdate);
        return bookMapper.fromEntityToDto(book);

    }
}
