package com.wennersanner.libraryapi.repository;

import com.wennersanner.libraryapi.model.Book;
import com.wennersanner.libraryapi.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query (value = "select case when (count(l.id) > 0) then true else false end " +
            "from Loan l where l.book = :book and (l.returned is null or l.returned is false)")
    boolean existsByBookAndNotReturned(@Param("book") Book book);
}
