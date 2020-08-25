package com.wennersanner.libraryapi.service;

import com.wennersanner.libraryapi.exceptions.BusinessException;
import com.wennersanner.libraryapi.model.Book;
import com.wennersanner.libraryapi.repository.BookRepository;
import com.wennersanner.libraryapi.service.impl.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {

    BookService service;

    @MockBean
    private BookRepository repository;

    @BeforeEach
    public void setUp() {
        service = new BookServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve salvar um livro")
    public void savedBookTest() {
        Book book = createValidBook ( );

        Mockito.when( repository.save(book)).thenReturn(
                        Book.builder()
                                .id(1l)
                                .author("Fulano")
                                .isbn("123")
                                .title("As aventuras").build());

        Book savedBook = service.save(book);

        Assertions.assertThat(savedBook.getId()).isNotNull();
        Assertions.assertThat(savedBook.getAuthor()).isEqualTo("Fulano");
        Assertions.assertThat(savedBook.getIsbn()).isEqualTo("123");
        Assertions.assertThat(savedBook.getTitle()).isEqualTo("As aventuras");
    }


    @Test
    @DisplayName ("Deve lançar um erro de negocio ao tentar salvar um livro com isbn duplicado")
    public void shouldNotSaveABookWithDuplicateISBN() {
        Book book = createValidBook ();

        Mockito.when ( repository.existsByIsbn(Mockito.anyString()) ).thenReturn(true);

        Throwable exception = Assertions.catchThrowable(() -> service.save(book));

        Assertions.assertThat (exception)
                .isInstanceOf(BusinessException.class)
                .hasMessage("isbn já cadastrado");

        Mockito.verify (repository, Mockito.never ()).save (book);
    }

    @Test
    @DisplayName("Deve obter um livro por id")
    public void  getBookByIdTest() {
        Long id = 1l;
        Book book = createValidBook();
        book.setId(id);
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(book));

        Optional<Book> foundBook = service.getById(id);

        Assertions.assertThat(foundBook.isPresent() ).isTrue();
        Assertions.assertThat(foundBook.get().getId()).isEqualTo(id);
        Assertions.assertThat(foundBook.get().getAuthor()).isEqualTo(book.getAuthor());
        Assertions.assertThat(foundBook.get().getTitle()).isEqualTo(book.getTitle());
        Assertions.assertThat(foundBook.get().getIsbn()).isEqualTo(book.getIsbn());

    }

    @Test
    @DisplayName("Deve retornar vazio ao obter um livro por id quandio ele não existe na base")
    public void bookNotFoundByIdTest() {
        Long id = 1l;

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<Book> book = service.getById(id);

        Assertions.assertThat(book.isPresent() ).isFalse();
    }


    @Test
    @DisplayName("Deve deletar um livro")
    public void deleteBookTest() {
        Book book = Book.builder().id(1l).build();

        //verifica lançamento de erro
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> service.delete(book));

        Mockito.verify(repository, Mockito.times(1)).delete(book);
    }

    @Test
    @DisplayName("Deve ocorrer erro ao tentat deletar livro inexistente")
    public void deleteIvalidBookTest() {
        Book book = new Book();

        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> service.delete(book));

        Mockito.verify(repository, Mockito.never()).delete(book);
    }

    @Test
    @DisplayName("Deve atualizar um livro")
    public void upadteBookTest() {
        Long id = 1l;

        //a atualizar
        Book updatingBook = Book.builder().id(id).build();

        //simulação
        Book updateBook = createValidBook();
        updateBook.setId(id);

        Mockito.when(repository.save(updatingBook)).thenReturn(updateBook);

        Book book = service.update(updatingBook);

       Assertions.assertThat(book.getId()).isEqualTo(updateBook.getId());
       Assertions.assertThat(book.getIsbn()).isEqualTo(updateBook.getIsbn());
       Assertions.assertThat(book.getAuthor()).isEqualTo(updateBook.getAuthor());
       Assertions.assertThat(book.getTitle()).isEqualTo(updateBook.getTitle());
    }

    @Test
    @DisplayName("Deve ocorrer erro ao tentar atualizar livro inexistente")
    public void updateIvalidBookTest() {
        Book book = new Book();

        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> service.update(book));

        Mockito.verify(repository, Mockito.never()).save(book);
    }

    @Test
    @DisplayName("Deve filtar livros pelas propriedades")
    public void findBookTest() {
        Book book = createValidBook();

        Pageable pageRequest = PageRequest.of(0, 10);

        List<Book> lista = Arrays.asList(book);
        Page<Book>  page = new PageImpl<Book>(lista, pageRequest, 1);

        Mockito.when(repository.findAll(Mockito.any(Example.class), Mockito.any(PageRequest.class)) )
                .thenReturn(page);

        Page<Book> result = service.find(book, pageRequest);

        Assertions.assertThat(result.getTotalElements()).isEqualTo(1);
        Assertions.assertThat(result.getContent()).isEqualTo(lista);
        Assertions.assertThat(result.getPageable().getPageNumber()).isEqualTo(0);
        Assertions.assertThat(result.getPageable().getPageSize()).isEqualTo(10);
    }

    @Test
    @DisplayName("Deve obter um livro pelo isbn")
    public void getBookByIsbnTest() {

        String isbn = "1230";
        Mockito.when(repository.findByIsbn(isbn)).thenReturn(Optional.of(Book.builder().id(1l).isbn(isbn).build()));

        Optional<Book> book = service.getBookByIsbn(isbn);

        Assertions.assertThat(book.isPresent()).isTrue();
        Assertions.assertThat(book.get().getId()).isEqualTo(1);
        Assertions.assertThat(book.get().getIsbn()).isEqualTo(isbn);
        Mockito.verify(repository, Mockito.times(1)).findByIsbn(isbn);
    }

    private Book createValidBook() {
        return Book.builder().isbn("123").author("Fulano").title("As aventuras").build();
    }

}
