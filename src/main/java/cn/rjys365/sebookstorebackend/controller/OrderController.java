package cn.rjys365.sebookstorebackend.controller;

import cn.rjys365.sebookstorebackend.datatypes.Order;
import cn.rjys365.sebookstorebackend.datatypes.OrderDigest;
import cn.rjys365.sebookstorebackend.util.OrdersConst;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("/orders/")
@CrossOrigin("http://localhost:3000")
public class OrderController {
    @GetMapping("/allOrders")
    public ArrayList<OrderDigest> getAllOrdersDigest(){
        ArrayList<OrderDigest> digests = new ArrayList<>();
        for(Order order:OrdersConst.orders){
            digests.add(order.getDigest());
        }
        return digests;
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Integer id){
        for(Order order:OrdersConst.orders){
            if(order.getId().equals(id)){
                return order;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Order Not Found");
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Order newOrder(@RequestBody Order order){
        //add(order);
        System.out.println(order);
        return order;
    }
}
