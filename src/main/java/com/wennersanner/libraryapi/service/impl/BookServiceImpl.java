package com.wennersanner.libraryapi.service.impl;

import com.wennersanner.libraryapi.exceptions.BusinessException;
import com.wennersanner.libraryapi.model.Book;
import com.wennersanner.libraryapi.respository.BookRepository;
import com.wennersanner.libraryapi.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {

        if (repository.existsByIsbn(book.getIsbn())) {
            throw  new BusinessException("isbn já cadastrado");
        }

        return  this.repository.save(book);
    }


}
