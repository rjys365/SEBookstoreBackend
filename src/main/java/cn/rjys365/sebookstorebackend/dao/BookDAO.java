package cn.rjys365.sebookstorebackend.dao;

import cn.rjys365.sebookstorebackend.entities.Book;
import cn.rjys365.sebookstorebackend.repositories.BookRepository;

import java.util.Optional;

public interface BookDAO {
    public Iterable<Book> getAllBooks();
    public Optional<Book> getBookById(Integer id);
}
