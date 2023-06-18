package cn.rjys365.sebookstorebackend.controller;

import cn.rjys365.sebookstorebackend.dto.OrderDetailsDTO;
import cn.rjys365.sebookstorebackend.entities.Book;
import cn.rjys365.sebookstorebackend.entities.Order;
import cn.rjys365.sebookstorebackend.dto.OrderDigest;
import cn.rjys365.sebookstorebackend.exception.OrderServiceException;
import cn.rjys365.sebookstorebackend.repositories.BookRepository;
import cn.rjys365.sebookstorebackend.repositories.OrderRepository;
import cn.rjys365.sebookstorebackend.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/orders/")
@CrossOrigin("http://localhost:3000")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/allOrders")
    public ArrayList<OrderDigest> getAllOrdersDigest(@RequestParam Integer userId){
        Iterable<Order> orders=this.orderService.getAllOrdersByUserId(userId);
        ArrayList<OrderDigest> digests=new ArrayList<>();
        orders.forEach(order -> {
            digests.add(new OrderDigest(order));
        });
        return digests;
    }

    @GetMapping("/{id}")
    public OrderDetailsDTO getOrder(@PathVariable Integer id){
        Optional<Order> orderOptional=this.orderService.getOrderById(id);
        if(orderOptional.isPresent()){
            return new OrderDetailsDTO(orderOptional.get());
        }
//        System.out.println("not found");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Order Not Found");
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDetailsDTO newOrder(@RequestBody Order order){
        try{
            return new OrderDetailsDTO(this.orderService.saveOrder(order));
        }
        catch(OrderServiceException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
