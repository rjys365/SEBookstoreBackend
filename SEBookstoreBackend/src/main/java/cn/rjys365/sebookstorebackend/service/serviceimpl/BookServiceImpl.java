package cn.rjys365.sebookstorebackend.service.serviceimpl;

import cn.rjys365.sebookstorebackend.dao.BookDAO;
import cn.rjys365.sebookstorebackend.dao.TagDAO;
import cn.rjys365.sebookstorebackend.entities.Book;
import cn.rjys365.sebookstorebackend.entities.TagNode;
import cn.rjys365.sebookstorebackend.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookDAO bookDAO;
    private final TagDAO tagDAO;

    public BookServiceImpl(BookDAO bookDAO, TagDAO tagDAO) {
        this.bookDAO = bookDAO;
        this.tagDAO = tagDAO;
    }

    public Iterable<Book> getAllBooks() {
        return this.bookDAO.getAllBooks();
    }

    public Optional<Book> getBookById(Integer id) {
        var bookOptional = this.bookDAO.getBookById(id);
        return bookOptional;
    }

    @Override
    public Optional<Book> saveBook(Book book) {
        return bookDAO.saveBook(book);
    }

    @Override
    public Boolean deleteBookById(Integer bookId) {
        Optional<Book> bookOptional = bookDAO.getBookById(bookId);
        if (bookOptional.isEmpty()) return false;
        return bookDAO.deleteBook(bookOptional.get());
    }

    @Override
    public List<TagNode> getRelatedTags(String name) {
        return this.tagDAO.getRelatedTags(name);
    }
}
