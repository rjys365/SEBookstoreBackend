package cn.rjys365.sebookstorebackend.controller;

import cn.rjys365.sebookstorebackend.datatypes.Book;
import cn.rjys365.sebookstorebackend.util.BookConst;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/books/")
@CrossOrigin("http://localhost:3000")
public class BookController {


    @GetMapping("/allBooks")
    public Book[] getAllBooks(){
        return BookConst.books;
    }



    @GetMapping("/{id}")
    public Book getBook(@PathVariable Integer id) {
        for (Book book : BookConst.books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Book Not Found");
    }

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "Hello World!";//test backend availability
    }

}
