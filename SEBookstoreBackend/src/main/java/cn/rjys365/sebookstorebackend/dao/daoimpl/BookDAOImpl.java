package cn.rjys365.sebookstorebackend.dao.daoimpl;

import cn.rjys365.sebookstorebackend.dao.BookDAO;
import cn.rjys365.sebookstorebackend.entities.Book;
import cn.rjys365.sebookstorebackend.entities.BookInfo;
import cn.rjys365.sebookstorebackend.repositories.BookMongoRepository;
import cn.rjys365.sebookstorebackend.repositories.BookRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDAOImpl implements BookDAO {

    private final BookRepository bookRepository;

    private final BookMongoRepository bookMongoRepository;

    public BookDAOImpl(BookRepository bookRepository, BookMongoRepository bookMongoRepository) {
        this.bookRepository = bookRepository;
        this.bookMongoRepository = bookMongoRepository;
    }

    public Iterable<Book> getAllBooks() {
        Iterable<Book> allBooks = this.bookRepository.findAll();
        List<Book> books = new ArrayList<>();
        for (Book book : allBooks) {
            fillBookInfo(book);
            books.add(book);
        }
        return books;
    }

    @Cacheable(value = "book", key = "#id", unless = "#result == null")
    public Optional<Book> getBookById(Integer id) {
        System.out.println("Getting book " + id + " from repository.");
        var bookOptional = this.bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            fillBookInfo(book);
            return Optional.of(book);
        }

        return Optional.empty();
    }

    private void fillBookInfo(Book book) {
        Optional<BookInfo> bookInfoOptional = this.bookMongoRepository.findById(book.getId());
        if (bookInfoOptional.isPresent()) {
            book.setBookInfo(bookInfoOptional.get());
        } else {
            BookInfo bookInfo = new BookInfo();
            bookInfo.setId(book.getId());
            bookInfo.setExtraInfo(new HashMap<>());
            bookInfo.setTags(new ArrayList<>());
            this.bookMongoRepository.save(bookInfo);
            book.setBookInfo(bookInfo);
        }
    }

    @CachePut(value = "book", key = "#book.id")
    public Optional<Book> saveBook(Book book) {
        try {
            BookInfo bookInfo = book.getBookInfo();
            if (bookInfo == null) {
                bookInfo = new BookInfo();
                bookInfo.setExtraInfo(new HashMap<>());
                bookInfo.setTags(new ArrayList<>());
            }
            bookRepository.save(book);
            bookInfo.setId(book.getId());
            this.bookMongoRepository.save(bookInfo);
            book.setBookInfo(bookInfo);
            return Optional.of(book);
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

    @Override
    @CacheEvict(value = "book", key = "#book.id")
    public Boolean deleteBook(Book book) {
        try {
            Optional<BookInfo> bookInfoOptional = this.bookMongoRepository.findById(book.getId());
            bookInfoOptional.ifPresent(this.bookMongoRepository::delete);
            bookRepository.delete(book);
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    @Override
    public List<Book> getBooksWithTags(List<String> tags) {
        List<BookInfo> bookInfos = this.bookMongoRepository.findByTagsIn(tags);
        List<Book> books = new ArrayList<>();
        for (BookInfo bookInfo : bookInfos) {
            Optional<Book> bookOptional = this.bookRepository.findById(bookInfo.getId());
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                book.setBookInfo(bookInfo);
                books.add(book);
            }
        }
        return books;
    }

    @Override
    public List<Book> getBooksWithTitleContaining(String title) {
        List<Book> books = this.bookRepository.findBooksByTitleContainingIgnoreCase(title);
        for (Book book : books) {
            fillBookInfo(book);
        }
        return books;
    }
}
