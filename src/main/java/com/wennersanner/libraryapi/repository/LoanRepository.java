package com.wennersanner.libraryapi.repository;

import com.wennersanner.libraryapi.model.Book;
import com.wennersanner.libraryapi.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    boolean existsByBookAndNotReturned(Book book);
}
