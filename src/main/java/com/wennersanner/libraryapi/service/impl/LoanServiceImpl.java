package com.wennersanner.libraryapi.service.impl;

import com.wennersanner.libraryapi.exceptions.BusinessException;
import com.wennersanner.libraryapi.model.Loan;
import com.wennersanner.libraryapi.repository.LoanRepository;
import com.wennersanner.libraryapi.service.LoanService;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {

    private LoanRepository repository;

    public LoanServiceImpl(LoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Loan save(Loan loan) {

        if (repository.existsByBookAndNotReturned(loan.getBook())) {
           throw new BusinessException("Book alreday loaned");
        }
        return repository.save(loan);
    }
}
