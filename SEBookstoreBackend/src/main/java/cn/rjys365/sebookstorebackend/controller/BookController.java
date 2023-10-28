package cn.rjys365.sebookstorebackend.controller;

import cn.rjys365.sebookstorebackend.entities.Book;
//import cn.rjys365.sebookstorebackend.util.BookConst;

import cn.rjys365.sebookstorebackend.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("/")
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

    @PatchMapping("/")
    public Book saveBook(@RequestBody Book book){
        if(book.getId()==null||bookService.getBookById(book.getId()).isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Patch Book");
        Optional<Book> bookOptional=bookService.saveBook(book);
        if(bookOptional.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Patch Book");
        return bookOptional.get();
    }

    @PostMapping("/")
    public Book createBook(@RequestBody Book book){
        if(book.getId()!=null)throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Patch Book");
        Optional<Book> bookOptional=bookService.saveBook(book);
        if(bookOptional.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Patch Book");
        return bookOptional.get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable Integer id){
        Boolean success = bookService.deleteBookById(id);
        if(success)return ResponseEntity.ok(true);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "Hello World!";//test backend availability
    }

}
