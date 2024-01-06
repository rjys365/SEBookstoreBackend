package cn.rjys365.sebookstorebackend.service;

import cn.rjys365.sebookstorebackend.entities.Book;
import cn.rjys365.sebookstorebackend.entities.TagNode;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Iterable<Book> getAllBooks();

    Optional<Book> getBookById(Integer id);

    Optional<Book> saveBook(Book book);

    Boolean deleteBookById(Integer bookId);

    List<TagNode> getRelatedTags(String name);

    List<Book> getBooksWithRelatedTags(String name);
}
