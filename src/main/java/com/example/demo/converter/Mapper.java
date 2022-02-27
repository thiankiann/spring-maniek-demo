package com.example.demo.converter;

import com.example.demo.model.Book;

public interface Mapper<E, D> {           //E-Entity D- Dto ( zgodnie z konwencjami Java)
    D fromEntityToDto(E entity);
    Book fromDtoToEntity(D dto);
}
