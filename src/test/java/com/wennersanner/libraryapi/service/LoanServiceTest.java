package com.wennersanner.libraryapi.service;

import com.wennersanner.libraryapi.exceptions.BusinessException;
import com.wennersanner.libraryapi.model.Book;
import com.wennersanner.libraryapi.model.Loan;
import com.wennersanner.libraryapi.repository.LoanRepository;
import com.wennersanner.libraryapi.service.impl.LoanServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("Test")
public class LoanServiceTest {

    LoanService service;

    @MockBean
    LoanRepository repository;

    @BeforeEach
    public void setUp() {
        this.service = new LoanServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve salvar um emprestimo")
    public void saveLoanTest() {
        Book book = Book.builder().id(1l).build();
        String fulano = "Fulano";

        Loan savingLoan = Loan.builder()
                .book(book)
                .customer(fulano)
                .loanDate(LocalDate.now() )
                .build();

        Loan savedLoan = Loan.builder()
                .id(1l)
                .loanDate(LocalDate.now())
                .customer(fulano)
                .book(book)
                .build();

        Mockito.when(repository.existsByBookAndNotReturned(book)).thenReturn(false);
        Mockito.when(repository.save(savingLoan) ).thenReturn(savedLoan);

        Loan loan = service.save(savingLoan);

       Assertions.assertThat(loan.getId()).isEqualTo(savedLoan.getId());
       Assertions.assertThat(loan.getBook().getId()).isEqualTo(savedLoan.getBook().getId());
       Assertions.assertThat(loan.getCustomer()).isEqualTo(savedLoan.getCustomer());
       Assertions.assertThat(loan.getLoanDate()).isEqualTo(savedLoan.getLoanDate());
    }

    @Test
    @DisplayName("Deve lançar erro ao salvar um empréstimo com um livro já emprestado")
    public void loanedBookSaveTest() {
        Book book = Book.builder().id(1l).build();
        String fulano = "Fulano";

        Loan savingLoan = Loan.builder()
                .book(book)
                .customer(fulano)
                .loanDate(LocalDate.now() )
                .build();

        Mockito.when(repository.existsByBookAndNotReturned(book)).thenReturn(true);
        Throwable exception = Assertions.catchThrowable(() -> service.save(savingLoan));

        Assertions.assertThat(exception)
                .isInstanceOf(BusinessException.class)
                .hasMessage("Book alreday loaned");

        Mockito.verify(repository, Mockito.never()).save(savingLoan);

    }
}
