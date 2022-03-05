package com.example.demo.converter;

import com.example.demo.dto.BookDto;
import com.example.demo.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class BookMapper implements Mapper<Book, BookDto> {

    private static final String space = " ";

    private static final Logger logger = LoggerFactory.getLogger(BookMapper.class);

    @Override
    public BookDto fromEntityToDto(Book entity) {
       var result =new BookDto(entity.getId(), entity.getName() + space + entity.getSurname(), entity.getTitle());
       logger.debug("converting entity : [{}] to dto; [{]]", entity, result);

        return result;
    }

    @Override
    public Book fromDtoToEntity(BookDto dto) {

        String authorNameAndSurname = dto.getAuthor();
        final int notFoundIndex = -1;
        int separatorIndex = nonNull(authorNameAndSurname) ? authorNameAndSurname.indexOf(space) : -1;

        String name ="";
        String surname ="";

        if(separatorIndex == notFoundIndex ) {
            surname = authorNameAndSurname;
        }else {
            name = authorNameAndSurname.substring(0,separatorIndex);
            surname = authorNameAndSurname.substring(separatorIndex+1);
        }

       var result =  new Book(dto.getId(), name,surname, dto.getTitle());
        logger.debug("convering dto: [{}] to entity: [{}]", dto, result);
        return result;
    }
}
