package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookCrudRepository extends CrudRepository<Book, Long> {

    @Query("from books ")
    List<Book> findAllBooks();    //na podstawie nazwy Spring tworzy zapytanie

    Optional<Book> findBookByTitle(String title);    // jak wyzej ale bardziej specyficzna metodka + zabezpiecza przed null-em (optional)

}
