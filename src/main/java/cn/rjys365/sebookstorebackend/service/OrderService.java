package cn.rjys365.sebookstorebackend.service;

import cn.rjys365.sebookstorebackend.entities.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    public List<Order> getAllOrdersByUserId(Integer userId);

    public List<Order> getAllOrders();

    public Optional<Order> getOrderById(Integer id);

    public Order saveOrder(Order order);

    public Optional<Order> createOrderFromUserCartItems(Integer userId);

    public Optional<Order> createOrderFromItem(Integer userId, Integer bookId, Integer quantity);

}
