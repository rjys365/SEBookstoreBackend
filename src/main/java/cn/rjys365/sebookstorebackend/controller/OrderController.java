package cn.rjys365.sebookstorebackend.controller;

import cn.rjys365.sebookstorebackend.entities.Book;
import cn.rjys365.sebookstorebackend.entities.Order;
import cn.rjys365.sebookstorebackend.datatypes.OrderDigest;
import cn.rjys365.sebookstorebackend.repositories.BookRepository;
import cn.rjys365.sebookstorebackend.repositories.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/orders/")
@CrossOrigin("http://localhost:3000")
public class OrderController {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    public OrderController(OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/allOrders")
    public ArrayList<OrderDigest> getAllOrdersDigest(){
        ArrayList<OrderDigest> digests = new ArrayList<>();
        Iterable<Order> orders = this.orderRepository.findAll();
        for(Order order:orders){
            digests.add(order.getDigest());
        }
        return digests;
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Integer id){
        Optional<Order> orderOptional=this.orderRepository.findById(id);
        if(orderOptional.isPresent()){
            return orderOptional.get();
        }
        System.out.println("not found");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Order Not Found");
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Order newOrder(@RequestBody Order order){
        if(order.getItems()==null||order.getItems().isEmpty())throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Empty Order");
        order.getItems().forEach(item->{
            if(item.getCount()==null||item.getCount()<=0)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid order item count");
            item.setOrder(order);
            Optional<Book> bookOptional = this.bookRepository.findById(item.getId());
            if(bookOptional.isEmpty())throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Non-existent order item");
            Book book = bookOptional.get();
            item.setBook(book);
        });
        //System.out.println(order);
        this.orderRepository.save(order);
        return order;
    }
}
