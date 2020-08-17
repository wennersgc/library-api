package com.wennersanner.libraryapi.repository;

import com.wennersanner.libraryapi.model.Book;
import com.wennersanner.libraryapi.respository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith (SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest //indica que feremos teste com jpa
public class BookRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    BookRepository repository;

    @Test
    @DisplayName ("Deve retornar verdadeiro quando existir um livro na base com o isbn informado")
    public void returnTrueWhenIsbExist() {

        String isbn = "123";

        Book book = Book.builder ().title ("Aventuras").author ("fulano").isbn ("123").build ();

        entityManager.persist (book);

        boolean exists = repository.existsByIsbn (isbn);

        Assertions.assertThat (exists) .isTrue ();
    }

    @Test
    @DisplayName ("Deve retornar falso quando não existir um livro na base com o isbn informado")
    public void returnFalseWhenIsbnDoesnTExist() {

        String isbn = "123";

        boolean exists = repository.existsByIsbn (isbn);

        Assertions.assertThat (exists) .isFalse ();
    }
}
