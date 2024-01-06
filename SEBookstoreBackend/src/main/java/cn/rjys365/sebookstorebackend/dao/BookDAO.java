package cn.rjys365.sebookstorebackend.dao;

import cn.rjys365.sebookstorebackend.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookDAO {
    Iterable<Book> getAllBooks();
    Optional<Book> getBookById(Integer id);
    Optional<Book> saveBook(Book book);
    Boolean deleteBook(Book book);
    List<Book> getBooksWithTags(List<String> tags);
}
