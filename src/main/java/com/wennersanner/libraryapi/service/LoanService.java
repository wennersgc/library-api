package com.wennersanner.libraryapi.service;

import com.wennersanner.libraryapi.model.Loan;

import java.util.Optional;

public interface LoanService {
    Loan save(Loan any);

    Optional<Loan> getById(Long id);

   Loan update(Loan loan);
}
