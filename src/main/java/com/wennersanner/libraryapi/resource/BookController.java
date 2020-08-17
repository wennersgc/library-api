package com.wennersanner.libraryapi.resource;

import com.wennersanner.libraryapi.dto.BookDTO;
import com.wennersanner.libraryapi.api.exceptions.ApiErrors;
import com.wennersanner.libraryapi.exceptions.BusinessException;
import com.wennersanner.libraryapi.model.Book;
import com.wennersanner.libraryapi.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
    public BookDTO create(@RequestBody @Valid BookDTO dto) {
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


    @GetMapping("/{id}")
    public BookDTO get(@PathVariable Long id) {
        Book book = service.getById(id).get();
        return modelMapper.map(book, BookDTO.class);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();

        return new ApiErrors(bindingResult);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors  handlerBusinessException(BusinessException ex) {
        return new ApiErrors(ex);
    }
}
