package com.wennersanner.libraryapi.service;

import com.wennersanner.libraryapi.model.Book;

import java.util.Optional;

public interface BookService {
    Book save(Book book);

    Optional<Book> getById(Long id);
}
