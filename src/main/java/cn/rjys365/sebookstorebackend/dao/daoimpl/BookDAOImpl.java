package cn.rjys365.sebookstorebackend.dao.daoimpl;

import cn.rjys365.sebookstorebackend.dao.BookDAO;
import cn.rjys365.sebookstorebackend.entities.Book;
import cn.rjys365.sebookstorebackend.repositories.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BookDAOImpl implements BookDAO {

    private final BookRepository bookRepository;

    public BookDAOImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    public Optional<Book> getBookById(Integer id) {
        return this.bookRepository.findById(id);
    }

    public Optional<Book> saveBook(Book book) {
        try {
            return Optional.of(bookRepository.save(book));
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean deleteBook(Book book) {
        try {
            bookRepository.delete(book);
            return true;
        } catch (Throwable e) {
            return false;
        }
    }
}
