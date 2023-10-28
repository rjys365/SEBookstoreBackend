package cn.rjys365.sebookstorebackend.dao.daoimpl;

import cn.rjys365.sebookstorebackend.dao.BookDAO;
import cn.rjys365.sebookstorebackend.entities.Book;
import cn.rjys365.sebookstorebackend.repositories.BookRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableCaching
public class BookDAOImpl implements BookDAO {

    private final BookRepository bookRepository;

    public BookDAOImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Cacheable(value = "book", key = "#id",unless="#result == null")
    public Optional<Book> getBookById(Integer id) {
        System.out.println("Getting book "+id+" from repository.");
        return this.bookRepository.findById(id);
    }

    @CachePut(value = "book", key = "#book.id")
    public Optional<Book> saveBook(Book book) {
        try {
            return Optional.of(bookRepository.save(book));
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

    @Override
    @CacheEvict(value = "book", key = "#book.id")
    public Boolean deleteBook(Book book) {
        try {
            bookRepository.delete(book);
            return true;
        } catch (Throwable e) {
            return false;
        }
    }
}
