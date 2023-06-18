package cn.rjys365.sebookstorebackend.dao.daoimpl;

import cn.rjys365.sebookstorebackend.dao.BookDAO;
import cn.rjys365.sebookstorebackend.entities.Book;
import cn.rjys365.sebookstorebackend.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BookDAOImpl implements BookDAO {

    private final BookRepository bookRepository;

    public BookDAOImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    public Iterable<Book> getAllBooks(){
        return this.bookRepository.findAll();
    }

    public Optional<Book> getBookById(Integer id){
        return this.bookRepository.findById(id);
    }
}
