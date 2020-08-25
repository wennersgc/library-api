package com.wennersanner.libraryapi.api.resource;

import com.wennersanner.libraryapi.api.dto.BookDTO;
import com.wennersanner.libraryapi.api.dto.LoanDTO;
import com.wennersanner.libraryapi.api.dto.LoanFilterDto;
import com.wennersanner.libraryapi.api.dto.ReturnedLoanDto;
import com.wennersanner.libraryapi.model.Book;
import com.wennersanner.libraryapi.model.Loan;
import com.wennersanner.libraryapi.service.BookService;
import com.wennersanner.libraryapi.service.LoanService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;
    private final BookService bookService;
    private final ModelMapper modelMapper;

    public LoanController(LoanService loanService, BookService bookService, ModelMapper modelMapper) {
        this.loanService = loanService;
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public Long create(@RequestBody LoanDTO dto) {

        Book book = bookService.getBookByIsbn(dto.getIsbn()).
                orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book not found for passed isbn"));

        Loan entity = Loan.builder()
                .book(book)
                .customer(dto.getCustomer())
                .loanDate(LocalDate.now())
                .build();

        entity = loanService.save(entity);

        return entity.getId();
    }

    @PatchMapping("{id}")
    public void returnBook(@PathVariable Long id, @RequestBody ReturnedLoanDto dto) {
        Loan loan = loanService.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        loan.setReturned(dto.getReturned());

        loanService.update(loan);
    }

    @GetMapping
    public Page<LoanDTO> find(LoanFilterDto dto, Pageable pageable) {
        Page<Loan> result = loanService.find(dto, pageable);

        List<LoanDTO>  loans = result
                .getContent()
                .stream()
                .map(entity -> {
                    Book book = entity.getBook();
                    BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
                    LoanDTO loanDTO = modelMapper.map(entity, LoanDTO.class);
                    loanDTO.setBook(bookDTO);
                    return loanDTO;

                }).collect(Collectors.toList());

        return new PageImpl<LoanDTO>(loans, pageable, result.getTotalElements());
    }

}
