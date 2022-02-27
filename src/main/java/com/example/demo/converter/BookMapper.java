package com.example.demo.converter;

import com.example.demo.dto.BookDto;
import com.example.demo.model.Book;

public class BookMapper implements Mapper<Book, BookDto> {


    @Override
    public BookDto fromEntityToDto(Book entity) {
        return new BookDto(entity.getId(), entity.getAuthor(), entity.getTitle());
    }

    @Override
    public Book fromDtoToEntity(BookDto dto) {
        return new Book(dto.getId(), dto.getAuthor(), dto.getTitle());
    }
}
