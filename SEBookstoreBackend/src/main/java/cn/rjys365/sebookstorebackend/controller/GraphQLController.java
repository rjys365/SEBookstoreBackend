package cn.rjys365.sebookstorebackend.controller;

import cn.rjys365.sebookstorebackend.entities.Book;
import cn.rjys365.sebookstorebackend.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLController {
    private final BookService bookService;

    public GraphQLController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public Iterable<Book> allBooks(){
        return this.bookService.getAllBooks();
    }

    @QueryMapping
    public List<Book> searchBooksByTitle(@Argument String titleContains){
        return this.bookService.findBookByTitleContaining(titleContains);
    }
}
