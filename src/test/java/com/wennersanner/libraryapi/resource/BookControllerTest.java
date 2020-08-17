package com.wennersanner.libraryapi.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wennersanner.libraryapi.dto.BookDTO;
import com.wennersanner.libraryapi.exceptions.BusinessException;
import com.wennersanner.libraryapi.model.Book;
import com.wennersanner.libraryapi.respository.BookRepository;
import com.wennersanner.libraryapi.service.BookService;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {

    static String BOOK_API = "/api/books";

    @Autowired
    MockMvc mvc;

    @MockBean
    BookService service;


    @Test
    @DisplayName("Deve criar um livro com sucesso")
    public void createBookTest() throws Exception{

        BookDTO dto = createNewBook();

        Book savedBook = createInvalidBook();

        BDDMockito.given(service.save(Mockito.any(Book.class))).willReturn(savedBook);

        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isCreated())
                .andExpect( MockMvcResultMatchers.jsonPath("id").value(1))
                .andExpect( MockMvcResultMatchers.jsonPath("title").value(dto.getTitle()))
                .andExpect( MockMvcResultMatchers.jsonPath("author").value(dto.getAuthor()))
                .andExpect( MockMvcResultMatchers.jsonPath("isbn").value(dto.getIsbn()))
        ;

    }

    //regra de integridade
    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados suficientes para criação do livro")
    public void createInvalidBookTest() throws Exception{

        String json = new ObjectMapper().writeValueAsString(new BookDTO());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest())
                //um mensagem de erro para cada proprieade obrigatoria
                .andExpect( MockMvcResultMatchers.jsonPath("erros",  Matchers.hasSize(3)));

    }

    //regra de negocio
    @Test
    @DisplayName("Deve lançar erro ao tentar cadastrar livro com isbn já utilizado por outro")
    public void createBookWithDuplicateIsbn() throws Exception{

        BookDTO dto = createNewBook();

        String json = new ObjectMapper().writeValueAsString(dto);

        String mensageErro = "isbn já cadastrado";

        BDDMockito.given(service.save(
                Mockito.any(Book.class)))
                .willThrow(new BusinessException(mensageErro));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("erros", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("erros[0]").value(mensageErro));

    }

    @Test
    @DisplayName("Deve obter informações de um livro")
    public void getBookDetailsTest() throws Exception {

        Long id = 1l;

        Book book = Book.builder()
                .id(id)
                .author(createNewBook().getAuthor())
                .title(createNewBook().getTitle())
                .isbn(createNewBook().getIsbn())
                .build();

        BDDMockito.given(service.getById(id)).willReturn(Optional.of(book));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(BOOK_API.concat("/" + id))
                .accept(MediaType.APPLICATION_JSON);


        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect( MockMvcResultMatchers.jsonPath("id").value(1))
                .andExpect( MockMvcResultMatchers.jsonPath("title").value(createNewBook().getTitle()))
                .andExpect( MockMvcResultMatchers.jsonPath("author").value(createNewBook().getAuthor()))
                .andExpect( MockMvcResultMatchers.jsonPath("isbn").value(createNewBook().getIsbn()));

    }


    private BookDTO createNewBook() {
        return BookDTO.builder().author("Artur").title("Meu livro").isbn("121212").build();
    }

    private Book createInvalidBook() {
        return Book.builder().id(Long.valueOf((long) 1)).author("Artur").title("Meu livro").isbn("121212").build();
    }
}
