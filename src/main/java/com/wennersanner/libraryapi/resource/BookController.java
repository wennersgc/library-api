package com.wennersanner.libraryapi.resource;

import com.wennersanner.libraryapi.dto.BookDTO;
import com.wennersanner.libraryapi.model.Book;
import com.wennersanner.libraryapi.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("ALL")
@RestController
@RequestMapping ("/api/books")
public class BookController {

    private BookService service;
    private ModelMapper modelMapper;

    public BookController(BookService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public BookDTO create(@RequestBody BookDTO dto) {
//        Book entity =
//                Book.builder()
//                    .title(dto.getTitle())
//                    .author(dto.getAuthor())
//                    .isbn(dto.getIsbn())
//                    .build();
        Book entity = modelMapper.map(dto, Book.class);

        entity = service.save(entity);

//        return  BookDTO.builder()
//                    .id(entity.getId())
//                    .title(entity.getTitle())
//                    .author(entity.getAuthor())
//                    .isbn(entity.getIsbn())
//

        return modelMapper.map(entity, BookDTO.class);
    }
}
