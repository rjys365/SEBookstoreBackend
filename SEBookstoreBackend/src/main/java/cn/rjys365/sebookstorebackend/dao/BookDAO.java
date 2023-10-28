package cn.rjys365.sebookstorebackend.dao;

import cn.rjys365.sebookstorebackend.entities.Book;

import java.util.Optional;

public interface BookDAO {
    public Iterable<Book> getAllBooks();
    public Optional<Book> getBookById(Integer id);
    public Optional<Book> saveBook(Book book);
    public Boolean deleteBook(Book book);
}
