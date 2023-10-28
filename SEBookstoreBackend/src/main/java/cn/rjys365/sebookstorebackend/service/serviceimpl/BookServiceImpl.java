package cn.rjys365.sebookstorebackend.service.serviceimpl;

import cn.rjys365.sebookstorebackend.dao.BookDAO;
import cn.rjys365.sebookstorebackend.entities.Book;
import cn.rjys365.sebookstorebackend.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public Iterable<Book> getAllBooks(){
        return this.bookDAO.getAllBooks();
    }

    public Optional<Book> getBookById(Integer id){
        return this.bookDAO.getBookById(id);
    }

    @Override
    public Optional<Book> saveBook(Book book) {
        return bookDAO.saveBook(book);
    }

    @Override
    public Boolean deleteBookById(Integer bookId) {
        Optional<Book> bookOptional=bookDAO.getBookById(bookId);
        if(bookOptional.isEmpty())return false;
        return bookDAO.deleteBook(bookOptional.get());
    }
}
