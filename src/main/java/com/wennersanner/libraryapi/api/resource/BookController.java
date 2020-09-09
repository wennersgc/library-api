package com.wennersanner.libraryapi.api.resource;

import com.wennersanner.libraryapi.api.dto.BookDTO;
import com.wennersanner.libraryapi.api.dto.LoanDTO;
import com.wennersanner.libraryapi.model.Book;
import com.wennersanner.libraryapi.model.Loan;
import com.wennersanner.libraryapi.service.BookService;
import com.wennersanner.libraryapi.service.LoanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@RestController
@RequestMapping ("/api/books")
@RequiredArgsConstructor
@Api("API Book")
@Slf4j
public class BookController {

    private final BookService service;
    private final ModelMapper modelMapper;
    private final LoanService loanService;

//    public BookController(BookService service, ModelMapper modelMapper) {
//        this.service = service;
//        this.modelMapper = modelMapper;
//    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    @ApiOperation("Cria um livro")
    public BookDTO create(@RequestBody @Valid BookDTO dto) {
        log.info("Criando um livro para o isbn: {}", dto.getIsbn());
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


    @GetMapping("{id}")
    @ApiOperation("Mostra detalhes de um livro pelo ID")
    public BookDTO get(@PathVariable Long id) {
        log.info("Obtendo um livro para o ID: {}", id);
        return service
                .getById(id)
                .map( book -> modelMapper.map(book, BookDTO.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Remove um livro pelo ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Livro removido com sucesso!")
    })
    public void delete(@PathVariable Long id) {
        Book book = service
                .getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND) );

        service.delete(book);
    }

    @PutMapping("{id}")
    @ApiOperation("Atualiza um livro pelo ID")
    public BookDTO update(@PathVariable Long id, @RequestBody @Valid BookDTO dto) {
       return service
                .getById(id).map( book ->{
                    book.setAuthor(dto.getAuthor());
                    book.setTitle(dto.getTitle());

                    book = service.update(book);

                    return modelMapper.map(book, BookDTO.class);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }

    @GetMapping
    @ApiOperation("Busca livros por parametros")
    public Page<BookDTO> find(BookDTO dto, Pageable pageRequest) {
        Book filter = modelMapper.map(dto, Book.class);
        Page<Book> result = service.find(filter, pageRequest);

        List<BookDTO> list = result.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, BookDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<BookDTO>(list, pageRequest, result.getTotalElements());
    }

    @GetMapping("{id}/loans")
    public Page<LoanDTO> loansByBook(@PathVariable Long id, Pageable pageable) {

        Book book = service.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Page<Loan> result = loanService.getLoansByBook(book, pageable);

        List<LoanDTO> list = result.getContent()
                .stream()
                .map(loan -> {
                    Book loanBook = loan.getBook();
                    BookDTO bookDTO = modelMapper.map(loanBook, BookDTO.class);
                    LoanDTO loanDTO = modelMapper.map(loan, LoanDTO.class);
                    loanDTO.setBook(bookDTO);

                    return loanDTO;
                }).collect(Collectors.toList());

        return new PageImpl<LoanDTO>(list, pageable, result.getTotalElements());
    }


}
