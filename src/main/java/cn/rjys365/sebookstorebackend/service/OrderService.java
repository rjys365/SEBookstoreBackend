package cn.rjys365.sebookstorebackend.service;

import cn.rjys365.sebookstorebackend.entities.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface OrderService {
    public Iterable<Order> getAllOrdersByUserId(Integer userId);

    public Optional<Order> getOrderById(Integer id);

    public Order saveOrder(Order order);
}
