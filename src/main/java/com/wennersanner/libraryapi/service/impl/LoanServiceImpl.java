package com.wennersanner.libraryapi.service.impl;

import com.wennersanner.libraryapi.api.dto.LoanFilterDto;
import com.wennersanner.libraryapi.exceptions.BusinessException;
import com.wennersanner.libraryapi.model.Book;
import com.wennersanner.libraryapi.model.Loan;
import com.wennersanner.libraryapi.repository.LoanRepository;
import com.wennersanner.libraryapi.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private LoanRepository repository;

    public  LoanServiceImpl(LoanRepository repository) {
        this.repository = repository;
    }


    @Override
    public Loan save(Loan loan) {

        if (repository.existsByBookAndNotReturned(loan.getBook())) {
           throw new BusinessException("Book alreday loaned");
        }
        return repository.save(loan);
    }

    @Override
    public Optional<Loan> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Loan update(Loan loan) {
        return repository.save(loan);
    }

    @Override
    public Page<Loan> find(LoanFilterDto filter, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Loan> getLoansByBook(Book book, Pageable pageable) {
        return repository.findByBook(book, pageable);
    }

    @Override
    public List<Loan> getAllLateLoans() {
       final Integer loanDays = 4;
        LocalDate threeDaysAgo = LocalDate.now().minusDays(loanDays);
       return repository.findByLoanDateLessThanAndNotReturned(threeDaysAgo);
    }
}
