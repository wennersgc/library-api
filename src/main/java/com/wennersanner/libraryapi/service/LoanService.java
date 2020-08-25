package com.wennersanner.libraryapi.service;

import com.wennersanner.libraryapi.api.dto.LoanFilterDto;
import com.wennersanner.libraryapi.model.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LoanService {
    Loan save(Loan any);

    Optional<Loan> getById(Long id);

   Loan update(Loan loan);

    Page<Loan> find(LoanFilterDto filter, Pageable pageable);
}
