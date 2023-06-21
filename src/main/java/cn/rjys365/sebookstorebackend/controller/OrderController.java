package cn.rjys365.sebookstorebackend.controller;

import cn.rjys365.sebookstorebackend.dto.CartItemRequest;
import cn.rjys365.sebookstorebackend.dto.OrderDetailsDTO;
import cn.rjys365.sebookstorebackend.entities.Order;
import cn.rjys365.sebookstorebackend.dto.OrderDigest;
import cn.rjys365.sebookstorebackend.service.OrderService;
import cn.rjys365.sebookstorebackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders/")
@CrossOrigin("http://localhost:3000")
public class OrderController {
    private final OrderService orderService;

    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/")
    public List<OrderDigest> getAllOrdersDigest(@RequestParam(required = false) Integer userId) {
        List<Order> orders;
        if (userId == null) orders = this.orderService.getAllOrders();
        else orders = this.orderService.getAllOrdersByUserId(userId);
        return orders.stream().map(OrderDigest::new).toList();
    }

    @GetMapping("/{id}")
    public OrderDetailsDTO getOrder(@PathVariable Integer id) {
        Optional<Order> orderOptional = this.orderService.getOrderById(id);
        if (orderOptional.isPresent()) {
            return new OrderDetailsDTO(orderOptional.get());
        }
//        System.out.println("not found");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Not Found");
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDetailsDTO newOrder(@RequestParam Integer userId, @RequestParam String from, @RequestBody(required = false) CartItemRequest item) {
        Optional<Order> orderOptional;
        if (from.equals("cart")) {
            orderOptional = this.orderService.createOrderFromUserCartItems(userId);
            if (orderOptional.isEmpty())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Illegal order creation");
            this.userService.setUserCartItems(userId,new ArrayList<>());
            return new OrderDetailsDTO(orderOptional.get());
        } else if (from.equals("item")) {
            if (item == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Illegal order creation");
            orderOptional = this.orderService.createOrderFromItem(userId, item.getId(), item.getQuantity());
            if (orderOptional.isEmpty())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Illegal order creation");
            return new OrderDetailsDTO(orderOptional.get());
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Illegal order creation");
    }
}
