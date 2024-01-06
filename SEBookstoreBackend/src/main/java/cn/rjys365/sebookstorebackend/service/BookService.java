package cn.rjys365.sebookstorebackend.service;

import cn.rjys365.sebookstorebackend.entities.Book;
import cn.rjys365.sebookstorebackend.entities.TagNode;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public Iterable<Book> getAllBooks();

    public Optional<Book> getBookById(Integer id);

    public Optional<Book> saveBook(Book book);

    public Boolean deleteBookById(Integer bookId);

    List<TagNode> getRelatedTags(String name);
}
