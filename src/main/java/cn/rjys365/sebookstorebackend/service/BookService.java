package cn.rjys365.sebookstorebackend.service;

import cn.rjys365.sebookstorebackend.entities.Book;

import java.util.Optional;

public interface BookService {
    public Iterable<Book> getAllBooks();

    public Optional<Book> getBookById(Integer id);
}
