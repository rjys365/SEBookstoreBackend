package cn.rjys365.sebookstorebackend.controller;

import cn.rjys365.sebookstorebackend.dao.BookDAO;
import cn.rjys365.sebookstorebackend.dao.daoimpl.BookDAOImpl;
import cn.rjys365.sebookstorebackend.entities.Book;
//import cn.rjys365.sebookstorebackend.util.BookConst;

import cn.rjys365.sebookstorebackend.repositories.BookRepository;
import cn.rjys365.sebookstorebackend.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@RestController
@RequestMapping("/books/")
@CrossOrigin("http://localhost:3000")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/allBooks")
    public Iterable<Book> getAllBooks(){
        return this.bookService.getAllBooks();
    }



    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) {
//        for (Book book : BookConst.books) {
//            if (book.getId().equals(id)) {
//                return book;
//            }
//        }
        Optional<Book> bookOptional=this.bookService.getBookById(id);
        if(bookOptional.isPresent()){
            return bookOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Book Not Found");
    }

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "Hello World!";//test backend availability
    }

}
