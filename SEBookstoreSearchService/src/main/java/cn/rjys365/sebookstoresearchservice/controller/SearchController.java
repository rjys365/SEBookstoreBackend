package cn.rjys365.sebookstoresearchservice.controller;

import cn.rjys365.sebookstoresearchservice.entity.Book;
import cn.rjys365.sebookstoresearchservice.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search/")
//@CrossOrigin("http://localhost:3000")
public class SearchController {
    private final BookRepository bookRepository;

    public SearchController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/author/")
    public List<Book> searchByAuthor(@RequestParam(name="name") String name){
        var result =bookRepository.findAllByAuthorIs(name);
        return result;
    }

    @GetMapping("/helloworld")
    public String helloWorld() {
        System.out.println("Hello World!");
        return "Hello World!";//test backend availability
    }
}
